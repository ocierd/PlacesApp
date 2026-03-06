package com.example.demo.controllers.filters;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.domain.Usuario;
import com.example.demo.services.interfaces.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filtro de autenticación JWT que intercepta las solicitudes HTTP entrantes,
 * extrae el token JWT del encabezado de autorización, valida el token y
 * establece la autenticación en el contexto de seguridad si el token es válido.
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    private final UserDetailsService userDetailsService;

    private final Logger logger = LoggerFactory.getLogger(JwtFilter.class);

    public JwtFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        processToken(request);

        logger.debug("Processing complete. Return back control to framework");

        // Pass the control back to framework
        filterChain.doFilter(request, response);
    }

    /**
     * Procesa el token JWT presente en el encabezado de autorización de la
     * solicitud. Si el token es válido, se establece la autenticación en el
     * contexto de seguridad.
     * 
     * @param request La solicitud HTTP entrante que contiene el encabezado de
     *                autorización.
     */
    private void processToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        logger.info("Authorization Header: {}", authHeader);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            logger.info("No Bearer Header, skip processing");
            return;
        }

        // Extract Bearer Token
        final String jwtToken = authHeader.substring(7);

        if (jwtService.isTokenExpired(jwtToken)) {
            logger.info("Token validity expired");
            return;
        }

        String userName = jwtService.getUserName(jwtToken);

        if (userName == null) {
            logger.info("No username found in JWT Token");
            return;
        }

        logger.info("Username found in JWT: " + userName);

        // Get existing authentication instance
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            logger.info("Already loggedin: " + userName);
            return;
        }

        // Authenticate and create authentication instance
        logger.info("Create authentication instance for {}", userName);
        Usuario user = (Usuario) userDetailsService.loadUserByUsername(userName);
        user.setPassword(null);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken((Usuario) user, null,
                user.getAuthorities());

        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // Store authentication token for application to use
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
