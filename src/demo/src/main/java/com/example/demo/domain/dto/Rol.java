package com.example.demo.domain.dto;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

public class Rol implements GrantedAuthority {
    
    private String rol;

    

    public String getRol() {
        return rol;
    }



    public void setRol(String rol) {
        this.rol = rol;
    }



    @Override
    public @Nullable String getAuthority() {
        return rol;
    }
    
}
