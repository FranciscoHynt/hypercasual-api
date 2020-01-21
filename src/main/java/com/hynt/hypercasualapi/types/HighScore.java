package com.hynt.hypercasualapi.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HighScore {

    private int score;
    private int time;
    private String player;
    private String country;
}