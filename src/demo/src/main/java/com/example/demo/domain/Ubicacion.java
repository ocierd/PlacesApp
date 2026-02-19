package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Ubicacion es una entidad que representa la ubicación geográfica de una
 * sucursal en la aplicación. Contiene información sobre la latitud, longitud y
 * un enlace a Google Maps. La clase está anotada con @Entity para indicar que
 * es una entidad de JPA y se mapea a la tabla "ubicacion" en la base de datos.
 */
@Entity
@Table(name = "ubicacion")
public class Ubicacion {

    /**
     * Identificador único de la ubicación. Es un campo generado automáticamente por
     * la base de datos y se mapea a la columna "ubicacion_id" en la tabla
     * "ubicacion".
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ubicacion_id")
    private Long ubicacionId;

    /**
     * Latitud de la ubicación. Es un campo que almacena la latitud geográfica de la
     * sucursal. Se mapea a la columna "latitud" en la tabla "ubicacion".
     */
    @Column(name = "latitud")
    private double latitud;

    /**
     * Longitud de la ubicación. Es un campo que almacena la longitud geográfica de
     * la sucursal. Se mapea a la columna "longitud" en la tabla "ubicacion".
     */
    @Column(name = "longitud")
    private double longitud;

    /**
     * Enlace a Google Maps. Es un campo que almacena un enlace a Google Maps para
     * la ubicación de la sucursal. Se mapea a la columna "enlace_maps" en la tabla
     * "ubicacion". Este campo es opcional y puede ser nulo.
     */
    @Column(name = "enlace_maps", nullable = true)
    private String enlaceMaps;



    public Long getUbicacionId() {
        return ubicacionId;
    }

    public void setUbicacionId(Long ubicacionId) {
        this.ubicacionId = ubicacionId;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public String getEnlaceMaps() {
        return enlaceMaps;
    }

    public void setEnlaceMaps(String enlaceMaps) {
        this.enlaceMaps = enlaceMaps;
    }

}
