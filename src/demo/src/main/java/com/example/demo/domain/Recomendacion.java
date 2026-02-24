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
@Table(name = "recomendacion")
public class Recomendacion {
    @Id
    @Column(name = "recomendacion_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recomendacioId;

    @Column(name = "aceptada")
    private Boolean aceptada;

    @Column(name = "recomendado_en")
    private Date recomendadoEn;

    @Column(name = "usuario_recomienda_id")
    private Long usuarioRecomiendaId;

    @Column(name = "usuario_recomendado_id")
    private Long usuarioRecomendadoId;

    @Column(name = "sucursal_id")
    private Long sucursalId;
    
    @ManyToOne
    @JoinColumn(name = "usuario_recomienda_id", insertable = false, updatable = false)
    private Usuario usuarioRecomienda;

    @ManyToOne
    @JoinColumn(name = "usuario_recomendado_id", insertable = false, updatable = false)
    private Usuario usuarioRecomendado;

    @ManyToOne
    @JoinColumn(name = "sucursal_id", insertable = false, updatable = false)
    private Sucursal sucursal;

    public Long getRecomendacioId() {
        return recomendacioId;
    }

    public void setRecomendacioId(Long recomendacioId) {
        this.recomendacioId = recomendacioId;
    }

    public Boolean getAceptada() {
        return aceptada;
    }

    public void setAceptada(Boolean aceptada) {
        this.aceptada = aceptada;
    }

    public Date getRecomendadoEn() {
        return recomendadoEn;
    }

    public void setRecomendadoEn(Date recomendadoEn) {
        this.recomendadoEn = recomendadoEn;
    }

    public Long getUsuarioRecomiendaId() {
        return usuarioRecomiendaId;
    }

    public void setUsuarioRecomiendaId(Long usuarioRecomiendaId) {
        this.usuarioRecomiendaId = usuarioRecomiendaId;
    }

    public Long getUsuarioRecomendadoId() {
        return usuarioRecomendadoId;
    }

    public void setUsuarioRecomendadoId(Long usuarioRecomendadoId) {
        this.usuarioRecomendadoId = usuarioRecomendadoId;
    }

    public Long getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(Long sucursalId) {
        this.sucursalId = sucursalId;
    }

    public Usuario getUsuarioRecomienda() {
        return usuarioRecomienda;
    }

    public void setUsuarioRecomienda(Usuario usuarioRecomienda) {
        this.usuarioRecomienda = usuarioRecomienda;
    }

    public Usuario getUsuarioRecomendado() {
        return usuarioRecomendado;
    }

    public void setUsuarioRecomendado(Usuario usuarioRecomendado) {
        this.usuarioRecomendado = usuarioRecomendado;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }


    
}

