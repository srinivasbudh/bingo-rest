package com.innovation.game.bingo.repository;

import com.innovation.game.bingo.model.CricketMatch;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface CricketMatchRepository extends MongoRepository<CricketMatch, Long> {
}
