package com.hynt.hypercasualapi.collections;

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
    private int maxScoresRecords;
    private String name;
    private String version;
}
