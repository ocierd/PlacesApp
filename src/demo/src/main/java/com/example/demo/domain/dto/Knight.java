package com.example.demo.domain.dto;

import org.springframework.stereotype.Component;

/**
 * Clase Knight que representa a un caballero con un arma
 */
@Component
public class Knight {
    
    private Weapon weapon;

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Knight(Weapon weapon) {
        this.weapon = weapon;
    }
}
