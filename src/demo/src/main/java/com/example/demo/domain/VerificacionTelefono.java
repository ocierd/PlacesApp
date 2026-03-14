package com.example.demo.domain;

import java.time.LocalDateTime;

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
@Table(name = "verificacion_telefono")
@Getter
@Setter
public class VerificacionTelefono {
    @Id
    @Column(name = "verificacion_telefono_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long verificacionTelefonoId;

    @Column(name = "codigo")
    private String codigo;

    @Column(name = "fecha_envio", insertable = true, updatable = false)
    private LocalDateTime fechaEnvio;

    @Column(name = "fecha_expiracion")
    private LocalDateTime fechaExpiracion;

    @Column(name = "fecha_confirmacion", insertable = false, nullable = true)
    private LocalDateTime fechaConfirmacion;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @ManyToOne
    @JoinColumn(name = "usuario_id", insertable = false, updatable = false)
    private Usuario usuario;


}
