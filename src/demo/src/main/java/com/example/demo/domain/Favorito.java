package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.Column;;

@Entity
@Table(name = "favorito")
@Getter
@Setter
public class Favorito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "favorito_id")
    private Long favoritoId;

    @Column(name = "usuario_id")
    private Long usuarioId;

    @Column(name = "sucursal_Id")
    private Long sucursalId;

    // @OneToOne
    // @JoinColumn(name = "usuario_id",nullable = true, insertable = true,updatable
    // = false)
    // private Usuario usuario;

    // @OneToOne
    // @JoinColumn(name = "sucursal_Id",nullable = true,insertable = true,updatable
    // = false)
    // private Sucursal sucursal;

    @OneToOne()
    @JoinColumn(name = "usuario_id", nullable = true, insertable = false, updatable = false)
    private Usuario usuario;

    @OneToOne
    @JoinColumn(name = "sucursal_Id", nullable = true, insertable = false, updatable = false)
    private Sucursal sucursal;

}
