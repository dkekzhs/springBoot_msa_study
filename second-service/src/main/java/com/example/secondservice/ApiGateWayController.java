package com.example.secondservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second-service")
public class ApiGateWayController {

    @GetMapping("/welcome")
    public String welcome(){
        return "first-service Welcome! ";
    }

}