package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/**
 * Modulo es una entidad que representa un módulo en la aplicación. 
 * Contiene información sobre el nombre del módulo, su descripción y la ruta asociada. La clase está anotada con @Entity para indicar que es una entidad de JPA y se mapea a la tabla "modulo" en la base de datos.
 */
@Entity
@Table(name = "modulo")
@Getter
@Setter
public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "modulo_id")
    private Short moduloId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "ruta")
    private String ruta;
    
}
