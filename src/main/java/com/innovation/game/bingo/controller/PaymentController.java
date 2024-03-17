package com.innovation.game.bingo.controller;

import com.innovation.game.bingo.model.*;
import com.innovation.game.bingo.service.PaymentService;
import com.innovation.game.bingo.service.SessionService;
import com.innovation.game.bingo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("bingo-rest/user/")
public class PaymentController {

    @Autowired
    private UserService userService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private SessionService sessionService;

    @RequestMapping(value = "payment", method = RequestMethod.POST)
    public String makePayment(String sender, String receiver, float amount) throws Throwable {
        return "Your updated balance is "+paymentService.makePayment(sender,receiver,amount);
    }

    @RequestMapping(value = "getBalance", method = RequestMethod.GET)
    public String register(String username) throws Throwable {

        return "Your balance is" + paymentService.getBalance(username);
    }
}
