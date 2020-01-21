package com.hynt.hypercasualapi.controllers;

import com.hynt.hypercasualapi.dto.HighScoreListDTO;
import com.hynt.hypercasualapi.services.ScoreService;
import com.hynt.hypercasualapi.types.HighScore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Type;
import java.util.List;

@Controller
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @RequestMapping(value = "topScores", method = RequestMethod.GET)
    public ResponseEntity topScores(@RequestParam String gameName,
                                    @RequestParam (required = false) Integer recordsAmount,
                                    @RequestParam (required = false) String countryName){

        return scoreService.selectTopScores(gameName, recordsAmount, countryName);
    }

    @RequestMapping(value = "insertScore", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity insertScore(@RequestParam String gameName,
                                      @RequestBody HighScore scoreToInsert){

        return scoreService.insertNewScore(gameName, scoreToInsert);
    }

    @RequestMapping(value = "syncScores", method = RequestMethod.POST)
    public ResponseEntity syncScores(@RequestParam String gameName,
                                     @RequestBody HighScoreListDTO highScoreList){

        return scoreService.syncScores(gameName, highScoreList);
    }
}
