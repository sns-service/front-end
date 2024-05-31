package com.example.front_end.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/mypage")
    public String myPage() {
        return "mypage";
    }

    @GetMapping("/signup")
    public String signUp() {
        return "signup";
    }
}