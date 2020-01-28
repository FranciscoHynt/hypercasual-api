package com.hynt.hypercasualapi.repositories;

import com.hynt.hypercasualapi.collections.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface GameRepository extends MongoRepository<Game, String> {

    Game findGameByName(String gameName);

    ArrayList<Game> findAllByName(String gameName);

    Game save(Game game);

}
