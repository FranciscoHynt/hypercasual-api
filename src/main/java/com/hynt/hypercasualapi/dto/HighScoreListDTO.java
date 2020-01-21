package com.hynt.hypercasualapi.dto;

import com.hynt.hypercasualapi.types.HighScore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@AllArgsConstructor
public class HighScoreListDTO {

    private ArrayList<HighScore> highScores;

}
