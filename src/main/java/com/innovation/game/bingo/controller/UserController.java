package com.innovation.game.bingo.controller;

import com.innovation.game.bingo.model.*;
import com.innovation.game.bingo.service.SessionService;
import com.innovation.game.bingo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("bingo-rest/user/")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public LoginResponse login(@RequestParam String username, @RequestParam String password) {
        AccountHolder findUser = userService.loginUser(username, password);
        LoginResponse loginResponse = new LoginResponse();

        if (findUser != null) {
            Session session = sessionService.createSession(username);
            loginResponse.setUsername(username);
            loginResponse.setLoginSuccess(true);
            loginResponse.setSessionId(session.getSessionId());
        } else {
            loginResponse.setLoginSuccess(false);
        }

        return loginResponse;
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public SingupResponse register(@RequestBody AccountHolder user) {
        user.setUsername(user.getUsername().toUpperCase());
        user.setPassword(user.getPassword());
        return userService.addUser(user);
    }

}
