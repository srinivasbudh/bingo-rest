package com.innovation.game.bingo.repository;

import com.innovation.game.bingo.model.BingoResult;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface ResultNumbersRepository extends MongoRepository<BingoResult,String> {
}
