package com.hynt.hypercasualapi.services;

import com.hynt.hypercasualapi.collections.Game;
import com.hynt.hypercasualapi.collections.HighScore;
import com.hynt.hypercasualapi.dto.HighScoreListDTO;
import com.hynt.hypercasualapi.repositories.GameRepository;
import com.hynt.hypercasualapi.repositories.HighscoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

@Service
public class ScoreService {

    final
    GameRepository gameRepository;

    final
    HighscoreRepository highscoreRepository;

    public ScoreService(GameRepository gameRepository, HighscoreRepository highscoreRepository) {
        this.gameRepository = gameRepository;
        this.highscoreRepository = highscoreRepository;
    }

    public ResponseEntity selectTopScores(String gameName, Integer recordsAmount, String countryName) {

        ArrayList<HighScore> highScores = highscoreRepository.findAllByGame_Name(gameName);

        return new ResponseEntity(new HighScoreListDTO(highScores), HttpStatus.OK);
    }

    public ResponseEntity insertNewScore(String gameName, HighScore scoreToInsert) {

        ArrayList<HighScore> highScores = getHighScoreList(gameName);

        boolean greaterThanSomeScore = highScores.stream().anyMatch(score -> score.getScore() < scoreToInsert.getScore());

        if(greaterThanSomeScore || highScores.isEmpty()){

            highScores.add(scoreToInsert);

            if(highScores.size() > 10) {

                Comparator<HighScore> comparator = Comparator.comparing(highScore -> highScore.getScore());
                highScores.remove(highScores.stream().min(comparator).get());
            }

            game.setHighScores(highScores);
            gameRepository.save(game);

            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity syncScores(String gameName, HighScoreListDTO highScoreList) {

        return null;
    }

    private ArrayList<HighScore> getHighScoreList(String gameName){

        return Optional.ofNullable(highscoreRepository.findAllByGame_Name(gameName)).orElse(new ArrayList<>());
    }
}
