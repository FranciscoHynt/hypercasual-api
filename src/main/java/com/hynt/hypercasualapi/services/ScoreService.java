package com.hynt.hypercasualapi.services;

import com.hynt.hypercasualapi.collections.Game;
import com.hynt.hypercasualapi.collections.HighScore;
import com.hynt.hypercasualapi.dto.HighScoreDTO;
import com.hynt.hypercasualapi.dto.HighScoreListDTO;
import com.hynt.hypercasualapi.repositories.GameRepository;
import com.hynt.hypercasualapi.repositories.HighscoreRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.convention.NamingConventions;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public ResponseEntity selectTopScores(String gameName, Integer recordsAmount) {

        Game game = gameRepository.findGameByName(gameName);

        int recordsToSelect = Optional.ofNullable(recordsAmount).orElse(game.getMaxScoresRecords());

        ArrayList<HighScore> highScores = getHighScoreList(gameName, recordsToSelect);

        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setSourceNamingConvention(NamingConventions.JAVABEANS_MUTATOR);

        ArrayList<HighScoreDTO> highScoresDTO = highScores
                .stream()
                .map(highScore -> mapper.map(highScore, HighScoreDTO.class))
                .collect(Collectors.toCollection(ArrayList::new));

        return new ResponseEntity(new HighScoreListDTO(highScoresDTO), HttpStatus.OK);
    }

    public ResponseEntity insertNewScore(String gameName, HighScoreDTO scoreToInsert){

        boolean hasSpaceInRecords = Boolean.FALSE;
        boolean greaterThanSomeScore = Boolean.FALSE;

        Game game = gameRepository.findGameByName(gameName);

        ArrayList<HighScore> highScores = getHighScoreList(gameName, game.getMaxScoresRecords());

        hasSpaceInRecords = (highScores.isEmpty() || highScores.size() < game.getMaxScoresRecords());

        if(!hasSpaceInRecords)
            greaterThanSomeScore = highScores.stream().anyMatch(score -> score.getScore() < scoreToInsert.getScore());

        if(hasSpaceInRecords || greaterThanSomeScore) {

            HighScore highScore = new HighScore();

            highScore.setGame(game);
            highScore.setScore(scoreToInsert.getScore());
            highScore.setPlayer(scoreToInsert.getPlayer());
            highScore.setTime(scoreToInsert.getTime());

            highscoreRepository.save(highScore);

            return new ResponseEntity(HttpStatus.CREATED);
        }

        return new ResponseEntity((HttpStatus.OK));

    }

    public ResponseEntity syncScores(String gameName, HighScoreListDTO highScoreList) {

        highScoreList.getHighScores().forEach(highScoreDTO -> insertNewScore(gameName, highScoreDTO));

        return new ResponseEntity(HttpStatus.OK);
    }

    private ArrayList<HighScore> getHighScoreList(String gameName, int recordsAmount){

        return Optional.ofNullable(highscoreRepository.findAllByGame_NameOrderByScoreDesc(gameName, PageRequest.of(0, recordsAmount))).orElse(new ArrayList<>());
    }
}