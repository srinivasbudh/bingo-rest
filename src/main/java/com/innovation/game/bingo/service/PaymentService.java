package com.innovation.game.bingo.service;

import com.innovation.game.bingo.exceptions.InsufficientBalanceException;
import com.innovation.game.bingo.model.AccountHolder;
import com.innovation.game.bingo.model.User;
import com.innovation.game.bingo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public float makePayment(String sender, String receiver, float amount) throws Throwable {
        if(!userRepository.findById(sender.toUpperCase()).isPresent() || !userRepository.findById(receiver.toUpperCase()).isPresent() || amount<0){
            throw new InsufficientBalanceException("Please check details again");
        }
        AccountHolder senderAccount = userService.getUser(sender.toUpperCase());
        AccountHolder receiverAccount = userService.getUser(receiver.toUpperCase());
        if ( senderAccount.getBalance()< amount) {
            throw new InsufficientBalanceException("Sender does not have sufficient funds to make payment.");
        }
        senderAccount.setBalance(senderAccount.getBalance() - amount);
        receiverAccount.setBalance(receiverAccount.getBalance() + amount);
        userRepository.save(senderAccount);
        userRepository.save(receiverAccount);
        return senderAccount.getBalance();
    }

    public float getBalance(String username) throws Throwable {
        if(!userRepository.findById(username.toUpperCase()).isPresent()){
            throw new InsufficientBalanceException("Please check details again");
        }

        AccountHolder userAccount = userService.getUser(username.toUpperCase());
        return userAccount.getBalance();
    }
}
