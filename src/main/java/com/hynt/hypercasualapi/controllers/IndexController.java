package com.hynt.hypercasualapi.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @RequestMapping(value = "/")
    public ResponseEntity index(){

        return new ResponseEntity("A API está online e pronta para receber requisições!", HttpStatus.OK);
    }

}
