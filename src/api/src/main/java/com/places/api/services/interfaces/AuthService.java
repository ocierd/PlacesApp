package com.places.api.services.interfaces;

import com.places.api.domain.dto.LoginDataDto;
import com.places.api.domain.dto.TokenDto;
import com.places.api.domain.exceptions.UnauthorizedException;

/**
 * Interfaz para el servicio de autenticación, que define el método para autenticar a un usuario
 */
public interface AuthService  {

    /**
     * Autentica al usuario con las credenciales proporcionadas en loginData y genera un token JWT si la autenticación es exitosa.
     * @param loginData Un objeto LoginDataDto que contiene el nombre de usuario y la contraseña del usuario.
     * @return Un objeto TokenDto que contiene el token JWT generado para el usuario autenticado.
     * @throws UnauthorizedException Si la autenticación falla debido a credenciales inválidas u otros problemas relacionados con la autenticación.
     */
    TokenDto auth(LoginDataDto loginData) throws UnauthorizedException;
}
