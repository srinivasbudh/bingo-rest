package com.innovation.game.bingo.service;

import com.innovation.game.bingo.model.BingoResult;
import com.innovation.game.bingo.model.Result;
import com.innovation.game.bingo.model.ResultResponse;
import com.innovation.game.bingo.model.YoutubeLink;
import com.innovation.game.bingo.repository.ResultNumbersRepository;
import com.innovation.game.bingo.repository.ResultRepository;
import com.innovation.game.bingo.repository.YoutubeLinkRepository;
import com.innovation.game.bingo.util.ValidateResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultService {

    @Autowired
    private ResultRepository resultRepository;
    @Autowired
    private ValidateResults validateResults;
    @Autowired
    private BingoService bingoService;
    @Autowired
    YoutubeLinkRepository youtubeLinkRepository;


    @Autowired
    private ResultNumbersRepository resultNumbersRepository;

    public Integer addBingoNumber(Integer takenBall) {
        BingoResult bingo = new BingoResult();
        bingo.setTakenBall(takenBall);
        return resultNumbersRepository.save(bingo).getTakenBall();
    }

    public String addYoutubeLink(String link) {
        YoutubeLink youtubeLink = new YoutubeLink();
        if(youtubeLinkRepository.count()==0){
            youtubeLink.setLink(link);
        }else{
            youtubeLinkRepository.deleteAll();
            youtubeLink.setLink(link);
        }
        return youtubeLinkRepository.save(youtubeLink).getLink();
    }

    public String getLink(){
        if(youtubeLinkRepository.count()==0){
            return "Sorry we dont have any live streaming now";
        }else{
            return youtubeLinkRepository.findAll().get(0).getLink();
        }
    }

    public boolean removeLink(){
         youtubeLinkRepository.deleteAll();
         return youtubeLinkRepository.count()==0;
    }

    public long removeDataFromResult(){
        resultNumbersRepository.deleteAll();
        return resultNumbersRepository.count();
    }

    public long removeDataFromBingoResult(){
        resultRepository.deleteAll();
        return resultRepository.count();
    }

    public List<String> getListOfWinners(){
         List<Result> totalResults=resultRepository.findByIsResultStatus(true);
        List<String> resultData = new ArrayList<>();
        for(Result temp:totalResults){
            resultData.add("Winner of "+temp.getResultType()+" is => "+temp.getUsername());
        }
        return resultData;
    }

    public List<Integer> getArrayResult() {
        List<BingoResult> bingos = resultNumbersRepository.findAll();
        if (bingos != null && !bingos.isEmpty()) {
            return bingos.stream().map(it -> it.getTakenBall()).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }

    public Boolean getNumber(Integer number) {
        List<BingoResult> bingos = resultNumbersRepository.findAll();
        if (bingos != null && !bingos.isEmpty()) {
            for (int i = 0; i < bingos.size(); i++) {
                System.out.println(bingos.get(i));
                if(bingos.get(i).getTakenBall()==number){
                    return true;
                }
            }
        } else {
            return false;
        }
        return false;
    }
    
    
    public List<Integer> setTakenNumbers(Integer takenBall) {
        BingoResult bingo = new BingoResult();
        addBingoNumber(takenBall);
        return getArrayResult();
    }

    public ResultResponse getResultByUserName(String userName, String resultType){
        ResultResponse resultResponse =new ResultResponse();
        Result result=new Result();
       List<Integer> userToken= bingoService.getAllBingos(userName).get(0);
        List<Integer> resultsToken= getArrayResult();
        if(!resultRepository.findById(resultType).isPresent()){
            System.out.println("entered 1 ");
               List<Integer> resultStatus = validateResults.forUser(userToken,resultsToken,resultType);
                result.setResultType(resultType);
                result.setIsResultStatus(resultStatus.isEmpty());
                resultResponseText(userName, resultType, resultResponse, result, resultStatus);

        }else{
            result =resultRepository.findById(resultType).get();
            if(result.isResultStatus()){
                    resultResponse.setSuccessful(false);
                    resultResponse.setMessage("Sorry we already have a winner for "+ resultType+" Bingo");
                    resultResponse.setUsername(userName);
                }else{
                System.out.println("entered 3 ");
                List<Integer> resultStatus = validateResults.forUser(userToken,resultsToken,resultType);
                    result.setIsResultStatus(resultStatus.isEmpty());
                    resultResponseText(userName, resultType, resultResponse, result, resultStatus);
                }
            }
            resultRepository.save(result);
        return resultResponse;
    }

    private void resultResponseText(String userName, String resultType, ResultResponse resultResponse, Result result, List<Integer> resultStatus) {
        if(resultStatus.isEmpty()){
            result.setUsername(userName);
            resultResponse.setMessage("Congratulations You are the winner of "+ resultType +" Bingo");
            resultResponse.setUsername(userName);
            resultResponse.setSuccessful(true);
        }else{
            if(resultStatus.size()==1){
                if(resultStatus.get(0)!=99999){
                    resultResponse.setMessage(resultStatus+" Number from your "+ resultType+" Bingo is still missing");
                }else{
                    resultResponse.setMessage("No Winners type for "+ resultType);
                }
            }else{
                resultResponse.setMessage(resultStatus+" Numbers from your "+ resultType+" Bingo are still missing");
            }
            resultResponse.setUsername(userName);
            resultResponse.setSuccessful(false);
        }
    }


}
