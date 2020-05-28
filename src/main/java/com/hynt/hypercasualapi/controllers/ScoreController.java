package com.hynt.hypercasualapi.controllers;

import com.hynt.hypercasualapi.dto.HighScoreDTO;
import com.hynt.hypercasualapi.dto.HighScoreListDTO;
import com.hynt.hypercasualapi.services.ScoreService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ScoreController {

    private static final String TOP_SCORES = "topScores";
    private static final String INSERT_SCORE = "insertScore";
    private static final String SYNC_SCORES = "syncScores";
    private static final String UPDATE_PLAYER_NAME = "updatePlayerName";

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @RequestMapping(value = TOP_SCORES, method = RequestMethod.GET)
    public ResponseEntity topScores(@RequestParam String gameName,
                                    @RequestParam (required = false) Integer recordsAmount){

        return scoreService.selectTopScores(gameName, recordsAmount);
    }

    @RequestMapping(value = INSERT_SCORE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public <T extends HighScoreDTO> ResponseEntity insertScore(@RequestParam String gameName,
                                                               @RequestBody T scoreToInsert){
        return scoreService.insertNewScore(gameName, scoreToInsert);
    }

    @RequestMapping(value = SYNC_SCORES, method = RequestMethod.POST, consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity syncScores(@RequestParam String gameName,
                                     @RequestBody HighScoreListDTO highScores){

        return scoreService.syncScores(gameName, highScores);
    }

    @RequestMapping(value = UPDATE_PLAYER_NAME, method = RequestMethod.POST)
    public ResponseEntity updatePlayerName(@RequestParam String gameName,
                                           @RequestParam String oldPlayerName,
                                           @RequestParam String newPlayerName){

        return scoreService.updatePlayerName(gameName, oldPlayerName, newPlayerName);
    }
}
