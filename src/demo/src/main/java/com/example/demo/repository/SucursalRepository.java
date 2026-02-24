package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Sucursal;

import jakarta.annotation.Nullable;

/**
 * SucursalRepository es una interfaz que extiende JpaRepository para
 * proporcionar
 * métodos de acceso a datos para la entidad Sucursal. Permite realizar
 * operaciones
 * CRUD (Crear, Leer, Actualizar, Eliminar) y consultas personalizadas en la
 * base de datos.
 */
@Repository
public interface SucursalRepository extends JpaRepository<Sucursal, Long> {

    /**
     * Crea una nueva sucursal en la base de datos utilizando un procedimiento
     * almacenado. Recibe los parámetros necesarios para crear la sucursal y
     * devuelve el ID de la sucursal creada.
     * 
     * @param empresaId      El ID de la empresa a la que pertenece la sucursal
     * @param nombreSucursal El nombre de la sucursal a crear
     * @param ubicacionId    El ID de la ubicación asociada a la sucursal (puede ser
     *                       null)
     * @param latitud        La latitud de la ubicación de la sucursal (puede ser
     *                       null)
     * @param longitud       La longitud de la ubicación de la sucursal (puede ser
     *                       null)
     * @param enlaceMaps     El enlace de Google Maps para la ubicación de la
     *                       sucursal (puede ser null)
     * @return El ID de la sucursal creada
     */
    @Procedure(procedureName = "sp_empresa_insert")
    Long crearSucursal(
            @Param("empresa_id") Integer empresaId,
            @Param("nombre_sucursal") String nombreSucursal,
            @Param("ubicacion_id") @Nullable Long ubicacionId,
            @Param("latitud") @Nullable Double latitud,
            @Param("longitud") @Nullable Double longitud,
            @Param("enlace_maps") @Nullable String enlaceMaps);


    /**
     * Obtiene la entidad por medio del identificador.
     * Regresa NULL en caso de no existir
     * 
     * @param sucursalId Id de sucursal
     * @return Sucursal o null si no existe
     */
    Sucursal findBySucursalId(Long sucursalId);

    
    /**
     * Obtiene una lista de sucursales por el ID de la empresa.
     * 
     * @param empresaId Id de la empresa
     * @return Lista de sucursales asociadas a la empresa
     */
    List<Sucursal> findByEmpresaId(Integer empresaId);

}
