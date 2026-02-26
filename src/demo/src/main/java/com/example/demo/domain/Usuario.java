package com.example.demo.domain;



import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
@Entity
@Table(name="usuario")
public class Usuario {

  @Id
  @Column(name = "usuario_id") 
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long usuarioid;

  @Column(name = "username") 
  private String username;

  @Column(name = "password") 
  private String password;

  @Column(name = "email")
  private String email; 

  @Column(name = "email_verificado",insertable = false) 
  private boolean emailVerificado;

  @Column(name = "nombre") 
  private String nombre;

  @Column(name = "apellido_paterno") 
  private String apellidoPaterno;

  @Column(name = "apellido_materno") 
  private String apellidoMaterno;

  @Column(name = "fecha_nacimiento") 
  private LocalDate fechaNacimiento;

  @Column(name = "telefono") 
  private String telefono;

  @Column(name = "telefono_verificado", insertable = false) 
  private boolean telefonoVerificado;

  @Column(name = "registrado_en", insertable = false) 
  private LocalDateTime regristradoEn;

  public Long getUsuarioid() {
    return usuarioid;
  }
  public void setUsuarioid(Long usuarioid) {
    this.usuarioid = usuarioid;
  }
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
  public boolean isEmailVerificado() {
    return emailVerificado;
  }
  public void setEmailVerificado(boolean emailVerificado) {
    this.emailVerificado = emailVerificado;
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
  public LocalDate getFechaNacimiento() {
    return fechaNacimiento;
  }
  public void setFechaNacimiento(LocalDate fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }
  public String getTelefono() {
    return telefono;
  }
  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }
  public boolean isTelefonoVerificado() {
    return telefonoVerificado;
  }
  public void setTelefonoVerificado(boolean telefonoVerificado) {
    this.telefonoVerificado = telefonoVerificado;
  }
  public LocalDateTime getRegristradoEn() {
    return regristradoEn;
  }
  public void setRegristradoEn(LocalDateTime regristradoEn) {
    this.regristradoEn = regristradoEn;
  }


    
}
