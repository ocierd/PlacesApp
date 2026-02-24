package com.example.demo.domain.dto;

import java.sql.Date;

import jakarta.persistence.Column;
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

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellidoPaterno() {
    return apellidoPaterno;
  }

  public void setApellidoPaterno(String apellidoPaterno) {
    this.apellidoPaterno = apellidoPaterno;
  }

  public String getApellidoMaterno() {
    return apellidoMaterno;
  }

  public void setApellidoMaterno(String apellidoMaterno) {
    this.apellidoMaterno = apellidoMaterno;
  }

  public Date getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(Date fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }




    
}
