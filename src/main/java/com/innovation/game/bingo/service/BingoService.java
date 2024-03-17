package com.innovation.game.bingo.service;

import com.innovation.game.bingo.model.Bingo;
import com.innovation.game.bingo.model.BingoResult;
import com.innovation.game.bingo.model.Contest;
import com.innovation.game.bingo.repository.BingoRepository;
import com.innovation.game.bingo.repository.ContestRepository;
import com.innovation.game.bingo.repository.ResultNumbersRepository;
import com.innovation.game.bingo.util.BingoGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BingoService {

    @Autowired
    private BingoGenerator bingoGenerator;
    @Autowired
    private BingoRepository bingoRepository;

    @Autowired
    private ContestRepository contestRepository;

    public List<Integer> generateBingo(String username, String contestId) {

        Contest contest = contestRepository.findByContestId(contestId)
                .orElseThrow(() -> new RuntimeException("Contest not found"));
       List<Bingo> existingBingoCard = bingoRepository.findByUsernameAndContestId(username.toUpperCase(), contestId.toUpperCase());
        if (!existingBingoCard.isEmpty()) {
            return existingBingoCard.get(0).getBingoNumber(); // Return existing Bingo card
        } else {
            Date expiration = new Date(System.currentTimeMillis() + 8 * 60 * 60 * 1000); // 8 hours from now
            Bingo newBingoCard = new Bingo();
            newBingoCard.setUsername(username.toUpperCase());
            newBingoCard.setBingoNumber(bingoGenerator.generateBingo());
            newBingoCard.setContestId(contestId);
            newBingoCard.setExpiration(expiration);
            newBingoCard.setStatus("valid");

            return bingoRepository.save(newBingoCard).getBingoNumber();
        }
    }

    public Long getBingoCount(String username) {
        return bingoRepository.countByUsername(username.toUpperCase());
    }

    public List<List<Integer>> getAllBingos(String username) {
        List<Bingo> bingos = bingoRepository.findByUsername(username.toUpperCase());
        if (bingos != null && !bingos.isEmpty()) {
            return bingos.stream().map(it -> it.getBingoNumber()).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }

    }

    public List<List<Integer>> BingoForContest(String username, String contestId) {
        List<Bingo> bingos = bingoRepository.findByUsername(username.toUpperCase());
        if (bingos != null && !bingos.isEmpty()) {
            return bingos.stream()
                    .filter(bingo -> bingo.getContestId().equalsIgnoreCase(contestId))
                    .map(Bingo::getBingoNumber)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }
    }


    public Contest createContest(Contest contest) {
        return contestRepository.save(contest);
    }

}
