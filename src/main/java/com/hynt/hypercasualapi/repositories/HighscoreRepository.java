package com.hynt.hypercasualapi.repositories;

import com.hynt.hypercasualapi.collections.HighScore;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface HighscoreRepository extends MongoRepository<HighScore, String> {

    ArrayList<HighScore> findAllByGame_NameOrderByScoreDesc(String gameName, Pageable pageable);

    ArrayList<HighScore> findAllByPlayerAndGame_Name(String playerName, String gameName);

    HighScore save(HighScore highScore);

}
