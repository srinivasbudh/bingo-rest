package com.innovation.game.bingo.repository;

import com.innovation.game.bingo.model.Contest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ContestRepository extends MongoRepository<Contest, String> {
    Optional<Contest> findByContestId(String contestId);

}
