package com.example.voter_service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {
    @GetMapping("/login")
    public String login() {
        return "login"; // templates/login.html (you already have this)
    }

    @GetMapping("/")
    public String home() {
        return "index"; // create a simple index.html later, or point to a dashboard
    }
}
