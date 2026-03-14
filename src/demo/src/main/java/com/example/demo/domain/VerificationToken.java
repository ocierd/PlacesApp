package com.example.demo.domain;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "verificacion_token")
public class VerificationToken {
  private static final int EXPIRATION = 60; // minutos

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "token")
  private String token;

  @OneToOne
  @JoinColumn(name = "usuario_id", nullable = false)
  private Usuario usuario;

  @Column(name = "expiry_date")
  private LocalDateTime expiryDate;

  // public VerificationToken(Usuario usuario) {
  // this.usuario = usuario;
  // this.expiryDate = LocalDateTime.now().plusMinutes(EXPIRATION); // Token
  // válido por 24h
  // this.token = UUID.randomUUID().toString(); // Genera un token aleatorio
  // seguro
  // }

  public VerificationToken() {
    
  }

  public VerificationToken(String token, Usuario usuario) {
    this.token = token;
    this.usuario = usuario;
    this.expiryDate = LocalDateTime.now().plusMinutes(EXPIRATION);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public LocalDateTime getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(LocalDateTime expiryDate) {
    this.expiryDate = expiryDate;
  }
}