package com.example.front_end.controller;

import com.example.front_end.dto.SocialPost;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    private final RestTemplate restTemplate;

    public HomeController() {
        this.restTemplate = new RestTemplate();
    }

    @GetMapping
    public String listAllFeed(Model model) {
        List<LinkedHashMap> posts = restTemplate.getForObject("http://localhost:8080/api/feeds/random", List.class);

        List<SocialPost> transformedPosts = posts.stream()
                .map(this::transformPost)
                .collect(Collectors.toList());

        model.addAttribute("posts", transformedPosts);
        return "index";
    }

    private SocialPost transformPost(LinkedHashMap post) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy. M. d. a hh:mm:ss");
        ZonedDateTime uploadDatetime = ZonedDateTime.parse((String) post.get("uploadDatetime"));
        String formattedDate = uploadDatetime.format(formatter);

        return new SocialPost(
                (int) post.get("feedId"),
                (String) post.get("imageId"),
                (String) post.get("uploaderName"),
                (int) post.get("uploaderId"),
                uploadDatetime,
                (String) post.get("contents"),
                ((Number) post.get("likes")).longValue(),
                formattedDate
        );
    }
}