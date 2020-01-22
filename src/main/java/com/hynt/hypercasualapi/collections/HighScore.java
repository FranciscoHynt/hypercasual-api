package com.hynt.hypercasualapi.collections;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "highscores")
@Data
public class HighScore {

    private String id;
    private Game game;
    private int score;
    private int time;
    private String player;
}