package com.example.demo.domain.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO para representar el token JWT y su información relacionada.
 */
@Getter
@Setter
public class TokenDto {


    private String token;

    private Integer expiresIn;

    private String refreshToken;

    private Integer refreshTokenExpiresIn;

}
