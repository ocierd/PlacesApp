package com.example.demo.domain;

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
@Table(name = "telefono")
@Getter
@Setter
public class Telefono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "telefono_id")
    private Long telefonoId;

    @Column(name = "numero")
    private String numero;

    @Column(name = "activo", insertable = false)
    private Boolean activo;

    @Column(name = "pais_id")
    private Short paisId;

    @ManyToOne
    @JoinColumn(name = "pais_id", insertable = false, updatable = false)
    private Pais pais;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @ManyToOne
    @JoinColumn(name = "usuario_id", insertable = false, updatable = false)
    private Usuario usuario;

}
