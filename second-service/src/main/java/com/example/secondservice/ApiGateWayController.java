package com.example.secondservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second-service")
@Slf4j
public class ApiGateWayController {

    @GetMapping("/welcome")
    public String welcome(){
        return "first-service Welcome! ";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("second-req") String header){
        log.info(header);
        return "first-service message" + header;
    }

}
