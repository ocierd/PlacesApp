package com.example.demo.services.interfaces;

import java.util.Map;

import com.example.demo.domain.Usuario;

/**
 * Interfaz que define los métodos para generar y validar tokens JWT en la
 * aplicación. Este servicio se encarga de crear tokens JWT para los usuarios
 * autenticados, así como de
 */
public interface JwtService {

    /**
     * Genera un token JWT para el usuario dado, con un tiempo de expiración
     * específico y reclamos adicionales.
     * 
     * @param user          El usuario para el cual se generará el token.
     * @param expireSeconds El tiempo en segundos después del cual el token
     *                      expirará.
     * @param extraClaims   Un mapa de claims adicionales que se incluirán en el
     *                      token.
     * @return El token JWT generado como una cadena.
     */
    String generarToken(Usuario user, long expireSeconds, Map<String, Object> extraClaims);

    /**
     * Genera un token JWT para el usuario dado, con un tiempo de expiración
     * específico.
     * 
     * @param user          El usuario para el cual se generará el token.
     * @param expireSeconds El tiempo en segundos después del cual el token
     *                      expirará.
     * @return El token JWT generado como una cadena.
     */
    String generarToken(Usuario user, long expireSeconds);

    /**
     * Valida si el token JWT proporcionado es válido y no ha expirado.
     * 
     * @param token El token JWT a validar.
     * @return true si el token ha expirado, false de lo contrario.
     */
    boolean isTokenExpired(String token);

    /**
     * Obtiene el nombre de usuario del token JWT proporcionado.
     * 
     * @param token El token JWT del cual se extraerá el nombre de usuario.
     * @return El nombre de usuario contenido en el token.
     */
    String getUserName(String token);

}
