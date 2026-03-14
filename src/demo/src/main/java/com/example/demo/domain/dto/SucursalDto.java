package com.example.demo.domain.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * SucursalDto es una clase que representa un Data Transfer Object (DTO) para la entidad Sucursal.
 * Esta clase se utiliza para transferir datos de sucursales entre el cliente y el servidor
 * de manera más eficiente, especialmente cuando se reciben datos desde el cliente en formato JSON. 
 * Contiene los campos necesarios para crear una nueva sucursal, como el nombre, 
 * el ID de la empresa a la que pertenece, la ubicación (ID, latitud, longitud) y un enlace a Google Maps.
 */
@Getter
@Setter
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


}
