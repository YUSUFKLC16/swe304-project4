package com.swe304.project4.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {

    @GetMapping("/api/status")
    public Map<String, String> getStatus() {
        return Map.of(
                "message", "SWE304 Project 4 application is running on Kubernetes2"
        );
    }
}
