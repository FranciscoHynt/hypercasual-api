package com.hynt.hypercasualapi.types;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HighScore {

    private String score;
    private String player;
    private String country;
}