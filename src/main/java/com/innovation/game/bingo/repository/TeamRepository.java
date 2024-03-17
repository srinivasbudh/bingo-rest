package com.innovation.game.bingo.repository;

import com.innovation.game.bingo.model.Team;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface TeamRepository extends MongoRepository<Team, Long> {
}