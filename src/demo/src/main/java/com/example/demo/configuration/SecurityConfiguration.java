package com.example.demo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.controllers.filters.JwtFilter;

/**
 * Clase de configuración de seguridad para la aplicación. Define las reglas de
 * autorización y autenticación, así como los filtros de seguridad necesarios
 * para proteger los endpoints de la aplicación.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    /**
     * Filtro de autenticación JWT que se utiliza para validar los tokens JWT en las
     * solicitudes entrantes.
     */
    private final JwtFilter jwtFilter;

    public SecurityConfiguration(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    /**
     * Configura la seguridad HTTP para la aplicación, definiendo las reglas de
     * autorización y autenticación.
     * 
     * @param http El objeto HttpSecurity utilizado para configurar la seguridad
     *             HTTP.
     * @return Un objeto SecurityFilterChain que define la cadena de filtros de
     *         seguridad para la aplicación.
     * @throws Exception Si ocurre un error durante la configuración de la seguridad
     *                   HTTP.
     */
    @Bean()
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(crfs -> crfs.disable())
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/usuarios").permitAll()
                        .requestMatchers(HttpMethod.POST, "/verificacion_email").permitAll()
                        .requestMatchers(HttpMethod.GET, "/verificacion_email").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        ;
        return http.build();
    }

    /**
     * Configura el AuthenticationManager para la aplicación, utilizando la
     * configuración de autenticación proporcionada por Spring Security.
     * 
     * @param authenticationConfiguration La configuración de autenticación
     *                                    proporcionada por Spring Security.
     * @return Un objeto AuthenticationManager que se utiliza para autenticar las
     *         credenciales del usuario.
     * @throws Exception Si ocurre un error durante la configuración del
     *                   AuthenticationManager.
     */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    /**
     * Configura el PasswordEncoder para la aplicación, utilizando
     * BCryptPasswordEncoder para codificar las contraseñas de los usuarios.
     * 
     * @return Un objeto PasswordEncoder que se utiliza para codificar las
     *         contraseñas de los usuarios.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
