package com.example.demo.services.interfaces;

import java.util.List;

import com.example.demo.domain.Horario;
import com.example.demo.domain.Sucursal;
import com.example.demo.domain.dto.SucursalDto;
import com.example.demo.domain.projections.SucursalSummary;
import com.example.demo.domain.dto.SucursalCriteriaDto;

/**
 * SucursalService es una interfaz que define los métodos de negocio
 * relacionados con las sucursales.
 * Proporciona operaciones para manejar las sucursales en la aplicación.
 */
public interface SucursalService {

    /**
     * Crea una nueva sucursal en la base de datos. Recibe un objeto Sucursal con
     * los datos de la sucursal a crear y devuelve la sucursal creada.
     * 
     * @param sucursal El objeto Sucursal que se desea crear
     * @return La sucursal creada
     */
    Sucursal crearSucursal(Sucursal sucursal);

    Sucursal agregarHorario(Long intSucursalId, Horario horario);

    /**
     * Crea una nueva sucursal en la base de datos a partir de un objeto
     * SucursalDto. Recibe un objeto SucursalDto con los datos de la sucursal a
     * crear y devuelve la sucursal creada. Este método es útil para recibir datos
     * de la sucursal desde el cliente en formato DTO (Data Transfer Object) y luego
     * convertirlo a una entidad Sucursal para guardarlo en la base de datos.
     * 
     * @param sucursal El objeto SucursalDto que se desea crear
     * @return La sucursal creada
     */
    Sucursal crearSucursal(SucursalDto sucursal);

    /**
     * Obtiene una lista de todas las sucursales disponibles.
     * 
     * @return Lista de todas las sucursales
     */
    List<Sucursal> getAllSucursales();

    List<SucursalSummary> getByCriteria(SucursalCriteriaDto sucursalRequest);

    /**
     * Obtiene la entidad por medio del identificador. Regresa NULL en caso de no
     * existir
     */
    Sucursal getById(Long sucursalId);

}
