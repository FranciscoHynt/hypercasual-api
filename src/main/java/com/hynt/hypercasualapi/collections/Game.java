package com.hynt.hypercasualapi.collections;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = Game.GAMES_COLLECTION)
@Data
public class Game {
    public static final String GAMES_COLLECTION = "games";

    @Id
    private String id;
    private int scoreModel;
    private int maxScoresRecords;
    private String name;
    private String version;
    private String password;
}
