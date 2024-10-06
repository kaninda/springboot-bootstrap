package com.aka.simplebootstrap.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatusController {
    @Value("${spring.application.name}")
    String appName;
    @Value("${server.port}")
    String portName;

    @GetMapping("/status")
    public String homePage(){
        return """
                Welcome to the application: %s
                Running on the server port: %s
                """.formatted( appName, portName);
    }
}
