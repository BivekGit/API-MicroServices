package com.ecommerce.taskservice.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class HomeController {
    @GetMapping("/tasks")
    public String home() {
        return "Welcome to Task Service";
    }
}
