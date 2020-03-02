package com.app.controller;

import com.app.model.dto.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestRestController {

    @GetMapping("/testUser")
    public Info<String> testUser() {
        return Info.<String>builder().data("TEST USER").build();
    }

    @GetMapping("/testAdmin")
    public Info<String> testAdmin() {
        return Info.<String>builder().data("TEST ADMIN").build();
    }
}
