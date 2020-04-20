package com.innovation.game.bingo.repository;

import com.innovation.game.bingo.model.Result;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface ResultRepository extends MongoRepository<Result, String> {
    List<Result> findByIsResultStatus(Boolean isResultStatus);
}
