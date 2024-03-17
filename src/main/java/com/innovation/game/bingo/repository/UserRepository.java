package com.innovation.game.bingo.repository;

import com.innovation.game.bingo.model.AccountHolder;
import com.innovation.game.bingo.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserRepository extends MongoRepository<AccountHolder, String> {
    User findByUsernameAndPassword(String username, String password);
}
