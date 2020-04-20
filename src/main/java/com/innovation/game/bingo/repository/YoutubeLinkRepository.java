package com.innovation.game.bingo.repository;

import com.innovation.game.bingo.model.YoutubeLink;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public interface YoutubeLinkRepository extends MongoRepository<YoutubeLink,String> {
}
