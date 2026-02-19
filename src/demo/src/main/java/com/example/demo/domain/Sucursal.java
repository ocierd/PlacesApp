package com.example.demo.domain;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Sucursal es una entidad que representa una sucursal de una empresa en la
 * aplicación. Contiene información sobre la sucursal, como su ID, nombre, fecha
 * de creación, empresa a la que pertenece y su ubicación.
 * La clase está anotada con @Entity para indicar que es una entidad de JPA y se
 * mapea a la tabla "sucursal" en la base
 */
@Entity
@Table(name = "sucursal")
public class Sucursal {

    /**
     * Identificador único de la sucursal
     * Es un campo generado automáticamente por la base de datos y se mapea a la
     * columna "sucursal_id" en la tabla "sucursal".
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sucursal_id")
    private Long sucursalId;

    /**
     * Nombre de la sucursal. Es un campo obligatorio que almacena el nombre de la
     * sucursal. Se mapea a la columna "nombre" en la tabla "sucursal".
     */
    @Column(name = "nombre")
    private String nombre;

    /**
     * Fecha de creación de la sucursal. Es un campo que almacena la fecha en que se
     * creó la sucursal. Se mapea a la columna "creado_en" en la tabla "sucursal".
     * Este campo es de solo lectura y se establece automáticamente al crear una
     * nueva sucursal, por lo que no se puede insertar ni actualizar manualmente.
     */
    @Column(name = "creado_en", insertable = false, updatable = false)
    private Date creadoEn;

    /**
     * Identificador de la empresa a la que pertenece la sucursal. Es un campo que
     * almacena el ID de la empresa a la que está asociada la sucursal. Se mapea a
     * la columna "empresa_id" en la tabla "sucursal". Este campo es de solo lectura
     * y se establece automáticamente al asociar una sucursal con una empresa, por
     * lo que no se puede insertar ni actualizar manualmente.
     */
    @Column(name = "empresa_id")
    private Integer empresaId;

    /**
     * Relación ManyToOne con la entidad Empresa. Indica que una sucursal pertenece
     * a una empresa. Se mapea a través de la columna "empresa_id" en la tabla
     * "sucursal". Esta relación es de solo lectura y se establece automáticamente
     * al asociar una sucursal con una empresa, por lo que no se puede insertar ni
     * actualizar manualmente.
     */
    @ManyToOne
    @JoinColumn(name = "empresa_id", insertable = false, updatable = false)
    private Empresa empresa;

    // @Column(name = "ubicacion_id")
    // private Long ubicacionId;

    /**
     * Relación OneToOne con la entidad Ubicacion. Indica que una sucursal tiene una
     * ubicación asociada. Se mapea a través de la columna "ubicacion_id" en la
     * tabla "sucursal". Esta relación permite insertar una nueva ubicación al crear una
     * sucursal, pero no permite actualizar la ubicación existente.
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ubicacion_id", nullable = true, insertable = true, updatable = false)
    private Ubicacion ubicacion;

    public Long getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(Long sucursalId) {
        this.sucursalId = sucursalId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getCreadoEn() {
        return creadoEn;
    }

    public void setCreadoEn(Date creadoEn) {
        this.creadoEn = creadoEn;
    }

    public Integer getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    // public Long getUbicacionId() {
    // return ubicacionId;
    // }

    // public void setUbicacionId(Long ubicacionId) {
    // this.ubicacionId = ubicacionId;
    // }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

}
