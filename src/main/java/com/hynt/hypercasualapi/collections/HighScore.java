package com.hynt.hypercasualapi.collections;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "highscores")
@Data
public class HighScore {

    @Id
    private String id;
    @Indexed(direction = IndexDirection.DESCENDING)
    private int score;
    private int time;
    private String player;
    private Game game;
}