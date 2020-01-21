package com.hynt.hypercasualapi.collections;

import com.hynt.hypercasualapi.types.HighScore;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "games")
@Data
public class Game {

    @Id
    private String id;
    private int scoreModel;
    private String name;
    private String version;
    private ArrayList<HighScore> highScores;

}
