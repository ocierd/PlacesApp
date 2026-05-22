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

/**
 * Menu es una entidad que representa un elemento del menú en la aplicación.
 * Contiene información sobre el nombre del menú, la ruta asociada, el ícono y
 * una referencia al menú padre en caso de que sea un submenú. La clase está
 * anotada con @Entity para indicar que es una entidad de JPA y se mapea a la
 * tabla "menu" en la base de datos.
 */
@Entity
@Table(name = "menu")
@Getter
@Setter
public class Menu {

    @Id
    @Column(name = "menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer menuId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "ruta")
    private String ruta;

    @Column(name = "icono")
    private String icono;

    @Column(name = "padre_menu_id")
    private Integer padreMenuId;

    @ManyToOne
    @JoinColumn(name = "padre_menu_id", insertable = false, updatable = false)
    private Menu padre;

    @Column(name = "modulo_id", nullable = true)
    private Short moduloId;

}
