package com.example.firstservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

@RestController
@RequestMapping("/first-service")
@Slf4j
public class ApiGateWayController {

    Environment env;
    @Autowired
    public ApiGateWayController(Environment env){
        this.env = env;
    }

    @GetMapping("/welcome")
    public String welcome(){
        return "first-service Welcome! ";
    }

    @GetMapping("/message")
    public String message(@RequestHeader("first-req") String header){
        log.info(header);
        return "first-service message" + header;
    }

    @GetMapping("/check")
    public String check(HttpServletRequest request){
        log.info("request Port : {}",request.getServerPort());
        return String.format("Hi , there. This is a message from first_service %s",
                env.getProperty("local.server.port"));
    }
}
