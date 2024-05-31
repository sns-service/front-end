package com.example.front_end.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PostController {

    @GetMapping("createpost")
    public String createPost() {
        return "createpost";
    }
}
