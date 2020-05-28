package com.hynt.hypercasualapi.controllers;

import com.hynt.hypercasualapi.services.VerificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class VerificationController {

    final VerificationService verificationService;

    public VerificationController(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @RequestMapping("profanityCheck")
    public ResponseEntity profanityCheck(@RequestParam String word){
        return verificationService.profanityCheck(word);
    }

}
