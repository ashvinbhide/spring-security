package com.ab.inmemorylogin.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/")
    public String defaultLogin() {
        return "using by default login...";
    }

    @GetMapping("/test")
    public String login() {
        return "success...";
    }
}
