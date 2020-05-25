package com.hynt.hypercasualapi.collections;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = HighScore.HIGHSCORES_COLLECTION)
@Data
public class HighScore {

    public static final String HIGHSCORES_COLLECTION = "highscores";
    @Id
    private String id;
    @Indexed(direction = IndexDirection.DESCENDING)
    private int score;
    private int time;
    private String player;
    private Game game;
}