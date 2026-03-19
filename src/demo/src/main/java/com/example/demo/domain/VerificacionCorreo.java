package com.example.demo.domain;

import org.hibernate.annotations.Generated;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "verificacion_correo")
@Getter
@Setter
public class VerificacionCorreo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "verificacion_correo_id")
  private Long verificacionCorreoId;

  @Generated
  @Column(name = "token", columnDefinition = "uniqueidentifier", insertable = false, updatable = false)
  private UUID token;

  @Generated
  @Column(name = "fecha_envio", insertable = true, updatable = false)
  private LocalDateTime fechaEnvio;

  @Column(name = "fecha_expiracion")
  private LocalDateTime fechaExpiracion;

  @Column(name = "fecha_confirmacion", insertable = false, nullable = true)
  private LocalDateTime fechaConfirmacion;

  @Column(name = "correo_id")
  private Long correoId;

  @ManyToOne
  @JoinColumn(name = "correo_id", insertable = false, updatable = false)
  private Correo correo;
}