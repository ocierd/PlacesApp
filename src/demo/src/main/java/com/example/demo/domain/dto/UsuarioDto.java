package com.example.demo.domain.dto;

import java.sql.Date;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto {

  @Column(name = "usuario_id") 
  private String usuarioId;

  @Column(name = "username") 
  private String username;

  @Column(name = "password") 
  private String password;

  @Column(name = "email")
  private String email; 
  
  @Column(name = "nombre") 
  private String nombre;

  @Column(name = "apellido_paterno") 
  private String apellidoPaterno;

  @Column(name = "apellido_materno") 
  private String apellidoMaterno;

  @Column(name = "fecha_nacimiento") 
  private Date fechaNacimiento;

  @Column(name = "telefono") 
  private String telefono;

    
}
