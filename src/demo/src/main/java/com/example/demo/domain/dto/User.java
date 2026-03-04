package com.example.demo.domain.dto;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class User implements UserDetails {
    private String username;
    private String password;

    private List<Rol> rols;

    // Implementa los métodos de UserDetails aquí

    @Override
    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return rols.stream().map(rol -> (GrantedAuthority) rol).toList();
    }

    public void setAuthorities(List<Rol> rols) {
        this.rols = rols;
    }

    // Implementa los demás métodos de UserDetails según sea necesario
    
}
