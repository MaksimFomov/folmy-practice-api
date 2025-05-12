package com.folmy.folmypractice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class TestController {
    @GetMapping("/user")
    public String userProfile() {
        return "Доступно для USER";
    }

    @GetMapping("/admin")
    public String adminProfile() {
        return "Доступно для ADMIN";
    }

    @GetMapping("/moderator")
    public String moderatorProfile() {
        return "Доступно для MODERATOR";
    }
}
