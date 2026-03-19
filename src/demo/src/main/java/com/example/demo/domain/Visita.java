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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "visita")
@Setter
@Getter
public class Visita {
   @Id
   @Column(name = "visita_id")
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long visitaId;

   @Column(name = "visitado_en", insertable=false)

   private Date visitadoEn;

   @Column(name = "visitado" , insertable=false)
   private boolean visitado;

   @Column(name = "comentario")
   private String comentario;

   @Column(name = "calificacion" , insertable=false)
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

}

