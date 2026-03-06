package com.example.demo.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.dto.LoginDataDto;
import com.example.demo.domain.dto.TokenDto;
import com.example.demo.domain.exceptions.UnauthorizedException;
import com.example.demo.services.interfaces.AuthService;

/**
 * Controlador REST encargado de manejar las solicitudes relacionadas con la
 * autenticación de usuarios. Proporciona un endpoint para que los usuarios
 * puedan iniciar sesión enviando sus credenciales, y devuelve un token JWT si
 * la autenticación es exitosa. Utiliza el servicio de autenticación
 * (AuthService) para realizar la lógica de autenticación y generación de
 * tokens.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Maneja las solicitudes POST al endpoint /auth/login para autenticar a los
     * usuarios. Recibe un objeto LoginDataDto con las credenciales del usuario, y
     * devuelve un TokenDto con el token JWT generado si la autenticación es
     * exitosa. Si la autenticación falla, se lanza una excepción
     * UnauthorizedException que es manejada por el GlobalExceptionHandler para
     * devolver una respuesta adecuada al cliente.
     * 
     * @param loginData Los datos de inicio de sesión del usuario.
     * @return Un TokenDto que contiene el token JWT generado.
     * @throws UnauthorizedException Si la autenticación falla.
     */
    @PostMapping("/login")
    public TokenDto login(@RequestBody LoginDataDto loginData) throws UnauthorizedException {
        var tokenData = authService.auth(loginData);
        return tokenData;
    }

}
