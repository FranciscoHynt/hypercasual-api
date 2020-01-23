package com.hynt.hypercasualapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HighScoreListDTO {

    private ArrayList<HighScoreDTO> highScores;

}
