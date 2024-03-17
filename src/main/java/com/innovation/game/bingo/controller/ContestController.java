package com.innovation.game.bingo.controller;

import com.innovation.game.bingo.model.*;
import com.innovation.game.bingo.service.BingoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("bingo-rest/contest/")
public class ContestController {

    @Autowired
    private BingoService bingoService;


    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<String> createContest(@RequestBody Contest contest) {
        contest.setContestId(contest.getContestId().toUpperCase());
        contest.setName(contest.getName().toUpperCase());

        Contest createdContest = bingoService.createContest(contest);
        if (createdContest != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Contest created successfully with ContestId " + createdContest.getContestId());
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Issue with contest creation");
        }
    }

}
