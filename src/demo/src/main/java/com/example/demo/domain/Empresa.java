package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Empresa es una entidad que representa una empresa en la aplicación. Contiene
 * información sobre la empresa, como su ID, nombre y categoría a la que
 * pertenece. La clase está anotada con @Entity para indicar que es una entidad
 * de JPA y se mapea a la tabla "empresa" en la base de datos.
 */
@Entity
@Table(name = "empresa")
public class Empresa {

    @Id
    @Column(name = "empresa_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer empresaId;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "categoria_id")
    private short categoriaId;

    @ManyToOne
    @JoinColumn(name = "categoria_id", insertable = false, updatable = false)
    private Categoria categoria;

    public Integer getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public short getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(short categoriaId) {
        this.categoriaId = categoriaId;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

}
