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

@Entity
@Table(name = "sms")
public class Sms {
    @Id
    @Column(name ="sms_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer smsId;

    @Column(name ="codigo")
    private String codigo;

    @Column(name ="fecha_envio" , insertable = false, updatable = false)
    private LocalDateTime fechaEnvio;

    @Column(name ="fecha_expiracion")
    private LocalDateTime fechaExpiracion;

    @Column(name ="fecha_confirmacion", insertable=false)
    private LocalDateTime fechaConfirmacion;

    @Column(name ="usuario_id")
    private Long usuarioId;

    @ManyToOne
    @JoinColumn(name = "usuario_id" , insertable = false, updatable = false)
    private Usuario usuario;

    public Integer getSmsId() {
        return smsId;
    }

    public void setSmsId(Integer smsId) {
        this.smsId = smsId;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public LocalDateTime getFechaExpiracion() {
        return fechaExpiracion;
    }

    public void setFechaExpiracion(LocalDateTime fechaExpiracion) {
        this.fechaExpiracion = fechaExpiracion;
    }

    public LocalDateTime getFechaConfirmacion() {
        return fechaConfirmacion;
    }

    public void setFechaConfirmacion(LocalDateTime fechaConfirmacion) {
        this.fechaConfirmacion = fechaConfirmacion;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
