package com.innovation.game.bingo.service;

import com.innovation.game.bingo.model.AccountHolder;
import com.innovation.game.bingo.model.SingupResponse;
import com.innovation.game.bingo.model.User;
import com.innovation.game.bingo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public SingupResponse addUser(AccountHolder user) {
        SingupResponse singupResponse = new SingupResponse();
        if (userRepository.findById(user.getUsername()).isPresent()) {
            singupResponse.setSuccessful(false);
            singupResponse.setMessage("user already exists");
        } else {
            userRepository.save(user);
            singupResponse.setSuccessful(true);
            singupResponse.setMessage("you have been registered successfully");
        }

        return singupResponse;
    }

    public AccountHolder getUser(String userId) {
        if (userRepository.findById(userId).isPresent()) {
            return userRepository.findById(userId).get();
        } else {
            return null;
        }
    }

    public AccountHolder loginUser(String userId, String password) {
        // Inside loginUser method
        Optional<AccountHolder> dbUserOpt = userRepository.findById(userId.toUpperCase());
        if (dbUserOpt.isPresent()) {
            AccountHolder dbUser = dbUserOpt.get();
            if (dbUser.getPassword().equals(password)) {
                // Passwords match
                return dbUser;
            } else {
                // Passwords don't match
                return null;
            }
        }

        return null;
    }

}
