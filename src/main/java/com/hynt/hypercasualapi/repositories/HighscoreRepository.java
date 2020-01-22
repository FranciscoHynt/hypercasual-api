package com.hynt.hypercasualapi.repositories;

import com.hynt.hypercasualapi.collections.HighScore;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.ArrayList;

public interface HighscoreRepository extends MongoRepository<HighScore, String> {

    ArrayList<HighScore> findAllByGame_Name(String gameName);

}
