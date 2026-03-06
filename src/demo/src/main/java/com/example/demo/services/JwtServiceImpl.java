package com.example.demo.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Usuario;
import com.example.demo.services.interfaces.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

/**
 * Servicio encargado de generar y validar tokens JWT para la autenticación de
 * usuarios. Utiliza una clave secreta para firmar los tokens y contiene métodos
 * para extraer información del token, como el nombre de usuario y verificar si
 * el token ha expirado.
 */
@Service
public class JwtServiceImpl implements JwtService {

    @Value("${places_app.jwt.secret}")
    private String SECRET_KEY;

    /**
     * Genera un token JWT con los claims adicionales, el nombre de usuario y el
     * tiempo de expiración especificados.
     * 
     * @param user          El usuario para el cual se generará el token.
     * @param expireSeconds El tiempo en segundos después del cual el token
     *                      expirará.
     * @return El token JWT generado como una cadena.
     */
    @Override
    public String generarToken(Usuario user, long expireSeconds) {
        Map<String, Object> claims = new HashMap<>();
        List<String> rols = user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList();
        claims.put("roles", rols);
        claims.put(Claims.ID, user.getUsuarioid().toString());
        return generarToken(user, expireSeconds, claims);
    }

    /**
     * Genera un token JWT con los claims adicionales, el nombre de usuario y el
     * tiempo de expiración especificados.
     * 
     * @param user          El usuario para el cual se generará el token.
     * @param expireSeconds El tiempo en segundos después del cual el token
     *                      expirará.
     * @param extraClaims   Un mapa de claims adicionales que se incluirán en el
     *                      token.
     * @return El token JWT generado como una cadena.
     */
    public String generarToken(Usuario user, long expireSeconds, Map<String, Object> extraClaims) {
        return Jwts
                .builder()
                .claims()
                .add(extraClaims)
                .and()
                .subject(user.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (expireSeconds * 1000)))
                .signWith(getSignInKey())
                .compact();
    }

    /**
     * Obtiene el nombre de usuario del token JWT proporcionado.
     * 
     * @param token El token JWT del cual se extraerá el nombre de usuario.
     * @return El nombre de usuario contenido en el token.
     */
    @Override
    public String getUserName(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    /**
     * Valida si el token JWT proporcionado es válido y no ha expirado.
     * 
     * @param token El token JWT a validar.
     * @return true si el token ha expirado, false de lo contrario.
     */
    @Override
    public boolean isTokenExpired(String token) {
        Claims claims = extractAllClaims(token);
        Date expiration = claims.getExpiration();
        return expiration.before(new Date());
    }

    private Claims extractAllClaims(String token) {
        // Extract claims after signature verification
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSignInKey() {

        // For simplicity, we are using a generated key here. In production, you should
        // use a secure and consistent key.
        // SecretKey key = Jwts.SIG.HS256.key().build();

        // De forma alternativa, si quieres usar una clave secreta personalizada:
        byte[] keyBytes = SECRET_KEY.getBytes();
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        return key;
    }

}
