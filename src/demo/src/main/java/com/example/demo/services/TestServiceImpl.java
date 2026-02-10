package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.domain.dto.Knight;
import com.example.demo.services.interfaces.TestService;

/**
 * Servicio
 */
@Service
public class TestServiceImpl implements TestService{

    /**
     * Inyección de dependencia de Knight
     */
    private final Knight knight;

    /**
     * Constructor
     * @param knight Inyección de dependencia
     */
    public TestServiceImpl(Knight knight) {
        this.knight = knight;
    }

    /**
     * Se implementa la interfaz gatGreeting
     */
    @Override
    public String getGreeting() {
        knight.getWeapon().attack();
        return "Hello from TestServiceImpl!";
    }
    
}
