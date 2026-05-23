package com.places.api.domain.dto;

import org.springframework.stereotype.Component;

/**
 * Clase Weapon que representa un arma
 */
@Component
public class Weapon {
    
    /**
     * Método attack que simula un ataque con el arma
     */
    public void attack() {
        System.out.println("Attacking with weapon!");
    }

}
