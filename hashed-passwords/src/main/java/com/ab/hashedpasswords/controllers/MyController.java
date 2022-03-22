package com.ab.hashedpasswords.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

    @GetMapping("/hello")
    public String hello() {

        return "success";
    }

    @PostMapping("/add")
    public String addUser() {
        return "success";
    }
}
