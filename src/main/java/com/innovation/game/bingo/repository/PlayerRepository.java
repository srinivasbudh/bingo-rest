package com.innovation.game.bingo.repository;

import com.innovation.game.bingo.model.Player;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface PlayerRepository extends MongoRepository<Player, Long> {
}