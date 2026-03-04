package com.example.demo.controllers.filters;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import com.example.demo.services.interfaces.AuthService;
import com.example.demo.services.interfaces.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    
    

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final AuthService authService;

    public JwtFilter(JwtService jwtService, AuthService authService) {
        // Auto inject dependent beans
        this.jwtService = jwtService;
        this.authService = authService;
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
        UserDetails userDetails = authService.loadUserByUsername(userName);

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,
                userDetails.getAuthorities());

        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        // Store authentication token for application to use
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}
