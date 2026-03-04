package com.example.demo.services;

import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.demo.domain.dto.LoginDataDto;
import com.example.demo.domain.dto.TokenDto;
import com.example.demo.domain.exceptions.AuthException;
import com.example.demo.services.interfaces.JwtService;
import com.example.demo.services.utils.StringUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${places_app.jwt.secret}")
    private String SECRET_KEY;

    /**
     * El tiempo de expiración para el token, en segundos
     */
    @Value("${places_app.jwt.expiration}")
    private Integer EXPIRATION_TIME;

    /**
     * El tiempo de expiración para el token de refresco, en segundos
     */
    @Value("${places_app.jwt.refresh_expiration}")
    private Integer REFRESH_EXPIRATION_TIME;

    @Override
    public TokenDto auth(LoginDataDto loginData) throws AuthException {

        if (!StringUtils.areEqual(loginData.getUsername(), "fer")) {
            throw new AuthException("Credenciales inválidas");
        }

        String token = generateToken(Map.of("role", "ROLE_USER"), loginData.getUsername(), EXPIRATION_TIME);
        String refreshToken = generateToken(Map.of("role", "ROLE_USER"), loginData.getUsername(), REFRESH_EXPIRATION_TIME);

        TokenDto tokenDto = new TokenDto();
        tokenDto.setToken(token);
        tokenDto.setRefreshToken(refreshToken); 
        tokenDto.setExpiresIn(EXPIRATION_TIME);
        tokenDto.setRefreshTokenExpiresIn(REFRESH_EXPIRATION_TIME);

        return tokenDto;
    }

    /**
     * Genera un token JWT con los claims adicionales, el nombre de usuario y el
     * tiempo de expiración especificados.
     * 
     * @param extraClaims    Un mapa de claims adicionales que se incluirán en el
     *                       token.
     * @param userName       El nombre de usuario que se establecerá como sujeto del
     *                       token.
     * @param expireSeconds El tiempo en segundos después del cual el token
     *                       expirará.
     * @return El token JWT generado como una cadena.
     */
    @Override
    public String generateToken(Map<String, String> extraClaims, String userName, long expireSeconds) {
        return Jwts
                .builder()
                .claims().add(extraClaims)
                .and()
                .subject(userName)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + (expireSeconds * 1000)))
                .signWith(getSignInKey())
                .compact();
    }

    @Override
    public String getUserName(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getSubject();
    }

    @Override
    public boolean isTokenExpired(String token) {
        Claims claims = extractAllClaims(token);
        return claims.getExpiration().before(new Date());
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
