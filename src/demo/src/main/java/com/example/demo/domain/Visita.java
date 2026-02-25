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
@Table(name = "visita")
public class Visita {
   @Id
   @Column(name = "visita_id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long visitaId;

   @Column(name = "visitado_en", insertable=false)

   private Date visitadoEn;

   @Column(name = "visitado")
   private boolean visitado;

   @Column(name = "comentario")
   private String comentario;

   @Column(name = "calificacion")
   private Short calificacion;

   @Column(name = "usuario_id")
   private Long usuarioId;

   @Column(name = "sucursal_id")
   private Long sucursalId;

   @ManyToOne
   @JoinColumn(name = "usuario_id", insertable = false, updatable = false)
   private Usuario usuario;

   @ManyToOne  
   @JoinColumn(name = "sucursal_id" , insertable = false, updatable = false)
   private Sucursal sucursal;

   public Long getVisitaId() {
    return visitaId;
   }

   public void setVisitaId(Long visitaId) {
    this.visitaId = visitaId;
   }

   public Date getVisitadoEn() {
    return visitadoEn;
   }

   public void setVisitadoEn(Date visitadoEn) {
    this.visitadoEn = visitadoEn;
   }

   public boolean isVisitado() {
    return visitado;
   }

   public void setVisitado(boolean visitado) {
    this.visitado = visitado;
   }

   public String getComentario() {
    return comentario;
   }

   public void setComentario(String comentario) {
    this.comentario = comentario;
   }

   public Short getCalificacion() {
    return calificacion;
   }

   public void setCalificacion(Short calificacion) {
    this.calificacion = calificacion;
   }

   public Long getUsuarioid() {
    return usuarioId;
   }

   public void setUsuarioid(Long usuarioid) {
    this.usuarioId = usuarioid;
   }

   public Usuario getUsuario() {
    return usuario;
   }

   public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
   }

   public Long getSucursalId() {
    return sucursalId;
   }

   public void setSucursalId(Long sucursalId) {
    this.sucursalId = sucursalId;
   }

   public Sucursal getSucursal() {
    return sucursal;
   }

   public void setSucursal(Sucursal sucursal) {
    this.sucursal = sucursal;
   }

}

