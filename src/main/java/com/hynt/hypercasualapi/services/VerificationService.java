package com.hynt.hypercasualapi.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class VerificationService {

    private String[] profanityWords = new String[]{"ass", "dick", "vagina", "fuck", "pica", "buceta", "caralho", "penis", "pênis"};

    public ResponseEntity profanityCheck(String word){
        for (String badWord: profanityWords) {
            if(badWord.contains(word))
                return new ResponseEntity(HttpStatus.CREATED);
        }

        return new ResponseEntity(HttpStatus.OK);
    }

}
