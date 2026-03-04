package com.example.demo.services.interfaces;

import java.util.Map;

import com.example.demo.domain.dto.LoginDataDto;
import com.example.demo.domain.dto.TokenDto;
import com.example.demo.domain.exceptions.AuthException;

public interface JwtService {

    TokenDto auth(LoginDataDto loginData) throws AuthException;

    String generateToken(Map<String, String> extraClaims, String userName, long expireInterval);

    boolean isTokenExpired(String token);

    String getUserName(String token);

}
