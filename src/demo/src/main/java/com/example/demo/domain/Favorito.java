package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Column;;

@Entity
@Table(name = "favorito")
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

    public Long getFavoritoId() {
        return favoritoId;
    }

    public void setFavoritoId(Long favoritoId) {
        this.favoritoId = favoritoId;
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

    // public void setUsuario(Usuario usuario) {
    //     this.usuario = usuario;
    // }

    public Sucursal getSucursal() {
        return sucursal;
    }

    // public void setSucursal(Sucursal sucursal) {
    //     this.sucursal = sucursal;
    // }

}
