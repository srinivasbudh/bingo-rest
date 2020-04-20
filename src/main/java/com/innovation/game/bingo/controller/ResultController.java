package com.innovation.game.bingo.controller;

import com.innovation.game.bingo.model.ResultResponse;
import com.innovation.game.bingo.model.Session;
import com.innovation.game.bingo.service.BingoService;
import com.innovation.game.bingo.service.ResultService;
import com.innovation.game.bingo.service.SessionService;
import com.innovation.game.bingo.util.PasswordAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("bingo-rest/bingo/")
public class ResultController {

    @Autowired
    private BingoService bingoService;

    @Autowired
    private ResultService resultService;

    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "get/takenNumbers", method = RequestMethod.GET)
    public List<Integer> getResultNumbers() {
        return resultService.getArrayResult();
    }

    @RequestMapping(value = "verifyNumberIsTaken", method = RequestMethod.GET)
    public Boolean getResultNumbers(@RequestParam Integer takenNumber) {
        return resultService.getNumber(takenNumber);
    }


    @RequestMapping(value = "setTakenNumber", method = RequestMethod.GET)
    public List<Integer> setTakenNumber(@RequestParam Integer takenNumber,String authCode) {
        PasswordAuthentication p= new PasswordAuthentication();
        if(p.authenticate(authCode.toUpperCase(),"$31$16$X61QdNBp6bDo5yiwTByklvRpqXy3HUuQrZu0vlR2gAQ")) {
            return resultService.setTakenNumbers(takenNumber);
        }else{
            return null;
        }

    }

    @RequestMapping(value = "getWinners", method = RequestMethod.GET)
    public ResultResponse getWinners(@RequestParam String resultType, @RequestParam String username) {
        if(sessionService.isUUID(username)){
            Session session = sessionService.getSession(username);
            return resultService.getResultByUserName(session.getUsername().toUpperCase(),resultType.toUpperCase());
        }else{
            return resultService.getResultByUserName(username,resultType.toUpperCase());
        }

    }

    @RequestMapping(value = "delete/ExistingTakenValues", method = RequestMethod.GET)
    public String deleteExistingValues(@RequestParam String authCode) {
        PasswordAuthentication p= new PasswordAuthentication();
        if(p.authenticate(authCode.toUpperCase(),"$31$16$X61QdNBp6bDo5yiwTByklvRpqXy3HUuQrZu0vlR2gAQ")){
            if(resultService.removeDataFromResult()==0){
                return "Previous results are cleared";
            }else{
                return "SomethingWentWrong";
            }
        }else{
            return "You are not Authorized to perform this action";
        }

    }

    @RequestMapping(value = "delete/ExistingResults", method = RequestMethod.GET)
    public String deleteExistingResults(@RequestParam String authCode) {
        PasswordAuthentication p= new PasswordAuthentication();
        if(p.authenticate(authCode.toUpperCase(),"$31$16$X61QdNBp6bDo5yiwTByklvRpqXy3HUuQrZu0vlR2gAQ")){
            if(resultService.removeDataFromBingoResult()==0){
                return "Previous results are cleared";
            }else{
                return "SomethingWentWrong";
            }
        }else{
            return "You are not Authorized to perform this action";
        }

    }

    @RequestMapping(value = "winnersList", method = RequestMethod.GET)
    public List<String> listWinners() {

        return resultService.getListOfWinners();

    }

}
