package com.hynt.hypercasualapi.services;

import com.hynt.hypercasualapi.collections.Game;
import com.hynt.hypercasualapi.types.HighScore;
import com.hynt.hypercasualapi.dto.HighScoreListDTO;
import com.hynt.hypercasualapi.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

import static com.hynt.hypercasualapi.utils.GameConstants.SIMPLE_SCORE_MODEL;

@Service
public class ScoreService {

    @Autowired
    GameRepository gameRepository;

    public ResponseEntity selectTopScores(String gameName, Integer recordsAmount, String countryName) {

        ArrayList<HighScore> highScores = gameRepository.findGameByName(gameName).getHighScores();

        return new ResponseEntity(new HighScoreListDTO(highScores), HttpStatus.OK);
    }

    public ResponseEntity insertNewScore(String gameName, HighScore scoreToInsert) {

        Game game = gameRepository.findGameByName(gameName);
        ArrayList<HighScore> highScores = getHighScoreList(game);

        boolean greaterThanSomeScore = highScores.stream().anyMatch(score -> score.getScore() < scoreToInsert.getScore());

        if(greaterThanSomeScore || highScores.size() == 0){

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

    private ArrayList<HighScore> getHighScoreList(Game game){

        return Optional.ofNullable(game.getHighScores()).orElse(new ArrayList<>());
    }
}
