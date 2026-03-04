package com.example.demo.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.demo.domain.dto.Rol;
import com.example.demo.domain.dto.User;
import com.example.demo.services.interfaces.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public UserDetails loadUserByUsername(String username) {
        // Implementa la lógica para cargar el usuario por su nombre de usuario

        User user = new User();
        user.setUsername(username);
        List<String> roles=List.of("ROLE_USER");
        user.setAuthorities(roles.stream().map(role -> {
            var rol = new Rol();
            rol.setRol(role);
            return rol;
        }).toList());
        return user; // Reemplaza con la implementación real
    }
    
    

}
