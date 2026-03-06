package com.example.demo.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Usuario;
import com.example.demo.domain.dto.LoginDataDto;
import com.example.demo.domain.dto.TokenDto;
import com.example.demo.domain.exceptions.UnauthorizedException;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.services.interfaces.AuthService;

/**
 * Implementación de AuthService que maneja la autenticación de usuarios
 * utilizando Spring Security. Este servicio se encarga de autenticar las
 * credenciales del usuario, generar tokens JWT para usuarios autenticados, y
 * manejar los tiempos de expiración de los tokens. Utiliza el
 * AuthenticationManager para autenticar las credenciales del usuario y el
 * JwtServiceImpl para generar los tokens JWT.
 */
@Service
public class AuthServiceImpl implements AuthService {

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

    /**
     * El AuthenticationManager utilizado para autenticar las credenciales del
     * usuario.
     */
    private final AuthenticationManager authenticationManager;

    /**
     * Utilizado para generar y validar tokens JWT.
     */
    private final JwtServiceImpl jwtService;

    /**
     * Constructor de AuthServiceImpl
     * 
     * @param authenticationManager El AuthenticationManager para autenticar las
     *                              credenciales del usuario.
     * @param usuarioRepository     El repositorio de usuarios para acceder a los
     *                              datos de los usuarios.
     * @param jwtService            El servicio de JWT para generar y validar
     *                              tokens.
     * @param passwordEncoder       El codificador de contraseñas para manejar las
     *                              contraseñas de los usuarios.
     */
    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            UsuarioRepository usuarioRepository, JwtServiceImpl jwtService, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    /**
     * Autentica al usuario con las credenciales proporcionadas en loginData y
     * genera un token JWT si la autenticación es exitosa.
     * 
     * @param loginData Un objeto LoginDataDto que contiene el nombre de usuario y
     *                  la contraseña del usuario.
     * @return Un TokenDto que contiene el token JWT generado, el token de refresco,
     *         y los tiempos de expiración correspondientes.
     * @throws UnauthorizedException Si la autenticación falla debido a credenciales
     *                               inválidas u otros problemas de autenticación.
     */
    @Override
    public TokenDto auth(LoginDataDto loginData) throws UnauthorizedException {
        var usernamePass = new UsernamePasswordAuthenticationToken(
                loginData.getUsername(), loginData.getPassword());

        // Autenticar las credenciales del usuario utilizando el AuthenticationManager
        // Internamente, Spring Security verificará las credenciales contra los detalles
        // del usuario cargados por UserDetailsServiceImpl
        Authentication auth = authenticationManager.authenticate(usernamePass);
        Usuario user = (Usuario) auth.getPrincipal();

        String token = jwtService.generarToken(user, EXPIRATION_TIME);
        String refreshToken = jwtService.generarToken(user, REFRESH_EXPIRATION_TIME);

        TokenDto tokenDto = new TokenDto();
        tokenDto.setToken(token);
        tokenDto.setRefreshToken(refreshToken);
        tokenDto.setExpiresIn(EXPIRATION_TIME);
        tokenDto.setRefreshTokenExpiresIn(REFRESH_EXPIRATION_TIME);

        return tokenDto;
    }

}
