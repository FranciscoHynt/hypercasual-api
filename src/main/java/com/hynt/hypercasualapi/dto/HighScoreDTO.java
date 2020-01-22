package com.hynt.hypercasualapi.dto;

import com.hynt.hypercasualapi.collections.Game;
import lombok.Getter;

@Getter
public class HighScoreDTO {

    private int score;
    private int time;
    private String player;
}
