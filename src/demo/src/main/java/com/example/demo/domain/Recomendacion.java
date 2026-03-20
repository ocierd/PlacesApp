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
@Table(name = "recomendacion")
@Getter
@Setter
public class Recomendacion {
    @Id
    @Column(name = "recomendacion_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recomendacioId;

    @Column(name = "aceptada", insertable=false)
    private Boolean aceptada;

    @Column(name = "recomendado_en", insertable=false)
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

    
}

