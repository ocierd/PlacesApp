package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.interfaces.TestService;


@RestController
@RequestMapping("/test")
public class TestController {

    private final TestService testService;
    /**
     * Test
     * @param testService
     */
    public TestController(TestService testService) {
        this.testService = testService;
    }

    /**
     * Primer API
     * @return Hola Mundo
     */
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    /**
     * API con interfaz
     * @return Saludo
     */
    @GetMapping("/greeting")
    public String getGreeting() {
        return this.testService.getGreeting();
    }
    
}
