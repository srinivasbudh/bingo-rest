package com.innovation.game.bingo.controller;

import com.innovation.game.bingo.model.Session;
import com.innovation.game.bingo.service.BingoService;
import com.innovation.game.bingo.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("bingo-rest/bingo/")
public class BingoController {

    @Autowired
    private BingoService bingoService;

    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public List<Integer> create(@RequestParam String sessionId) {
        Session session = sessionService.getSession(sessionId);
        if (session != null) {
            return bingoService.generateBingo(session.getUsername());
        } else {
            return null;
        }
    }

    @RequestMapping(value = "count", method = RequestMethod.GET)
    public Long count(@RequestParam String sessionId) {
        Session session = sessionService.getSession(sessionId);
        if (session != null) {
            return bingoService.getBingoCount(session.getUsername());
        } else {
            return null;
        }
    }

    @RequestMapping(value = "count/Guest", method = RequestMethod.GET)
    public Long countGuest(@RequestParam String username) {
        return bingoService.getBingoCount(username );
    }

    @RequestMapping(value = "get", method = RequestMethod.GET)
    public List<List<Integer>> get(@RequestParam String sessionId) {
        Session session = sessionService.getSession(sessionId);
        if (session != null) {
            return bingoService.getAllBingos(session.getUsername());
        } else {
            return null;
        }
    }

    @RequestMapping(value = "get/username", method = RequestMethod.GET)
    public List<List<Integer>> getWithUserName(@RequestParam String username) {

            return bingoService.getAllBingos(username);
    }
    @RequestMapping(value = "create/guest", method = RequestMethod.GET)
    public List<Integer> createAsGuest(@RequestParam String username) {
        if(countGuest(username)==0){
            return bingoService.generateBingo(username);
        }else{
            return bingoService.getAllBingos(username).get(0);
        }

    }
}
