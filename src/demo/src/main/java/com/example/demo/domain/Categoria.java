package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Clase Categoria que representa una categoría de productos
 */
@Entity
@Table(name = "categoria")
public class Categoria {

    /**
     * Identificador único de la categoría
     */
    @Id
    @Column(name = "categoria_id")
    private short categoriaId;

    /**
     * Nombre de la categoría
     */
    @Column(name = "nombre")
    private String nombre;

    /**
     * Descripción de la categoría
     */
    @Column(name = "descripcion", nullable = true)
    private String descripcion;


    
    public short getCategoria_id() {
        return categoriaId;
    }

    public void setCategoria_id(short categoria_id) {
        this.categoriaId = categoria_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
