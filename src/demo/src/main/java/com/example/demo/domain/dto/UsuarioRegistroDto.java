package com.example.demo.domain.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioRegistroDto {

    private String username;

    private String password;

    private String email;

    private String telefono;
    
    private Short paisId;

    private String nombre;

    private String apellidoPaterno;

    private String apellidoMaterno;

    private LocalDate fechaNacimiento;
}
