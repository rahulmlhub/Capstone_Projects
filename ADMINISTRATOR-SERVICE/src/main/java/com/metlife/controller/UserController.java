package com.metlife.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/hi")
    public String sayHi(){
        return "HI";
    }

    @GetMapping("/hello")
    public String hello(){
        return "HELLO";
    }


}
