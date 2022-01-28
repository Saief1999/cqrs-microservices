package com.example.gatewayservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/fallback")
@RestController
public class FallbackController {
    @GetMapping("/commands")
    public String fallbackCommands() {
        return "fallback from commands";
    }

    @GetMapping("/queries")
    public String fallbackQueries() {
        return "fallback from queries";
    }
}
