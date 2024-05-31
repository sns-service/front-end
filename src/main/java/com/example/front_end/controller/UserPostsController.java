package com.example.front_end.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserPostsController {

    @GetMapping("/userposts")
    public String getUserPosts() {
        return "userposts";
    }
}
