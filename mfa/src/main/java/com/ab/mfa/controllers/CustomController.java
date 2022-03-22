package com.ab.mfa.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomController {

    @GetMapping("/test")
    public String hello() {
        return "hello!";
    }
}
