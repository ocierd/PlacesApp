package com.example.demo.repository;

import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Sucursal;
import com.example.demo.domain.projections.SucursalSummary;

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
    @Procedure(procedureName = "sp_sucursal_insert")
    Long crearSucursal(
            @Param("empresa_id") Integer empresaId,
            @Param("nombre_sucursal") String nombreSucursal,
            @Param("ubicacion_id") @Nullable Long ubicacionId,
            @Param("latitud") @Nullable Double latitud,
            @Param("longitud") @Nullable Double longitud,
            @Param("enlace_maps") @Nullable String enlaceMaps);

    @Procedure(procedureName = "sp_horario_insert")
    Long crearHorarioSucursal(
            @Param("sucursal_id") Long sucursalId,
            @Param("dia_id") Short diaId,
            @Param("hora_apertura") LocalTime horaApertura,
            @Param("hora_cierre") LocalTime horaCierre);

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

    @Procedure(procedureName = "get_sucursal_by_criteria")
    //@Query(value = "exec get_sucursal_by_criteria", nativeQuery = true)
    List<SucursalSummary> findByCriteria(
            @Param("nombre") @Nullable String nombre,
            @Param("latitud") @Nullable Double latitud,
            @Param("longitud") @Nullable Double longitud,
            @Param("kilometros") @Nullable Double kilometros);


    /**
     * Obtiene una lista de sucursales que coinciden con el criterio de búsqueda. El criterio de búsqueda se aplica a los nombres de las sucursales, las empresas a las que pertenec
     * @param criteria El criterio de búsqueda para filtrar las sucursales por nombre, empresa o categoría. Puede ser null o vacío para obtener todas las sucursales.
     * @return Lista de sucursales que coinciden con el criterio de búsqueda, proyectadas como SucursalSummary (ID y nombre).
     */
    @Query(value = "SELECT s.sucursal_id, s.nombre \r\n" + //
            "FROM sucursal s \r\n" + //
            "INNER JOIN empresa e on e.empresa_id = s.empresa_id \r\n" + //
            "INNER JOIN categoria c on c.categoria_id = e.categoria_id \r\n" + //
            "WHERE s.nombre + e.nombre + c.nombre LIKE %:criteria% ", nativeQuery = true)
    List<SucursalSummary> findSucursalesByCriteria(@Param("criteria") @Nullable String criteria);
}