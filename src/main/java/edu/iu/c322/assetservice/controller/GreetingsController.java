package edu.iu.c322.assetservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class GreetingsController {

    @GetMapping
    public String greetings() {
        return "Greetings from the licensing service!";
    }

}

