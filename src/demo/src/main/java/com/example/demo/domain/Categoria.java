package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Clase Categoria que representa una categoría de productos
 */
@Entity
@Table(name = "categoria")
@Getter
@Setter
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


}
