package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.services.interfaces.TestService;

/**
 * Servicio
 */
@Service
public class TestServiceImpl implements TestService{

    /**
     * Se implementa la interfaz gatGreeting
     */
    @Override
    public String getGreeting() {
        return "Hello from TestServiceImpl!";
    }
    
}
