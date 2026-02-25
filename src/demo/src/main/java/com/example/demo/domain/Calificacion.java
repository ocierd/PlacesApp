package com.example.demo.domain;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "calificacion")
public class Calificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="calificacion_Id")
    private Long calificacionId;

    @Column(name = "puntaje")
    private Integer puntaje;

    @Column (name = "calificado_en", insertable = false)
    private Date calificadoEn;

    @Column (name = "comentario")
    private String comentario;

    @Column( name ="usuario_id")
    private Long usuarioId;

    @Column (name = "sucursal_id")
    private Long sucursalId;

    @ManyToOne
    @JoinColumn(name = "usuario_id", insertable = false,updatable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "sucursa_id", insertable = false,updatable = false)
    private Sucursal sucursal;

    public Long getCalificacionId() {
        return calificacionId;
    }

    public void setCalificacionId(Long calificacionId) {
        this.calificacionId = calificacionId;
    }

    public Integer getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Integer puntaje) {
        this.puntaje = puntaje;
    }

    public Date getCalificadoEn() {
        return calificadoEn;
    }

    public void setCalificadoEn(Date calificadoEn) {
        this.calificadoEn = calificadoEn;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(Long sucursalId) {
        this.sucursalId = sucursalId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }
}
