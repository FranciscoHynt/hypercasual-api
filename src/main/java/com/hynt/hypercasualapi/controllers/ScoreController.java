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

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @RequestMapping(value = "topScores", method = RequestMethod.GET)
    public ResponseEntity topScores(@RequestParam String gameName,
                                    @RequestParam (required = false) Integer recordsAmount){

        return scoreService.selectTopScores(gameName, recordsAmount);
    }

    @RequestMapping(value = "insertScore", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity insertScore(@RequestParam String gameName,
                                      @RequestBody HighScoreDTO scoreToInsert){

        return scoreService.insertNewScore(gameName, scoreToInsert);
    }

    @RequestMapping(value = "syncScores", method = RequestMethod.POST, consumes =  MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity syncScores(@RequestParam String gameName,
                                     @RequestBody HighScoreListDTO highScores){

        return scoreService.syncScores(gameName, highScores);
    }

    @RequestMapping(value = "updatePlayerName", method = RequestMethod.POST)
    public ResponseEntity updatePlayerName(@RequestParam String gameName,
                                           @RequestParam String oldPlayerName,
                                           @RequestParam String newPlayerName){

        return scoreService.updatePlayerName(gameName, oldPlayerName, newPlayerName);
    }
}
