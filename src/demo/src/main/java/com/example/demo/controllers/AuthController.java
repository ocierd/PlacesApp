package com.example.demo.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.dto.LoginDataDto;
import com.example.demo.domain.dto.TokenDto;
import com.example.demo.domain.exceptions.AuthException;
import com.example.demo.services.interfaces.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private JwtService jwtService;


    public AuthController(JwtService jwtService) {
        this.jwtService = jwtService;
    }


    @PostMapping("/login")
    public TokenDto login(@RequestBody LoginDataDto loginData) throws AuthException {
        var tokenData= jwtService.auth(loginData);
        return tokenData;
    }

}
