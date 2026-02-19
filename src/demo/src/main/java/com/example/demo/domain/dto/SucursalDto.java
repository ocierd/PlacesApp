package com.example.demo.domain.dto;

/**
 * SucursalDto es una clase que representa un Data Transfer Object (DTO) para la entidad Sucursal.
 * Esta clase se utiliza para transferir datos de sucursales entre el cliente y el servidor
 * de manera más eficiente, especialmente cuando se reciben datos desde el cliente en formato JSON. 
 * Contiene los campos necesarios para crear una nueva sucursal, como el nombre, 
 * el ID de la empresa a la que pertenece, la ubicación (ID, latitud, longitud) y un enlace a Google Maps.
 */
public class SucursalDto {
    /**
     * Nombre de la sucursal.
     */
    private String nombre;
    /**
     * Identificador de la empresa a la que pertenece la sucursal.
     */
    private Integer empresaId;

    /**
     * Identificador de la ubicación.
     */
    private Long ubicacionId;

    /**
     * Latitud de la ubicación de la sucursal.
     */
    private Double latitud;

    /**
     * Longitud de la ubicación de la sucursal.
    */
    private Double longitud;

    /**
     * Enlace a Google Maps para la ubicación de la sucursal.
     */
    private String enlaceMaps;


    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Integer getEmpresaId() {
        return empresaId;
    }
    public void setEmpresaId(Integer empresaId) {
        this.empresaId = empresaId;
    }
    public Long getUbicacionId() {
        return ubicacionId;
    }
    public void setUbicacionId(Long ubicacionId) {
        this.ubicacionId = ubicacionId;
    }
    public Double getLatitud() {
        return latitud;
    }
    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }
    public Double getLongitud() {
        return longitud;
    }
    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
    public String getEnlaceMaps() {
        return enlaceMaps;
    }
    public void setEnlaceMaps(String enlaceMaps) {
        this.enlaceMaps = enlaceMaps;
    }

}
