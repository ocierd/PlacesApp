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
@Table(name = "calificacion")
@Getter
@Setter
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


}
