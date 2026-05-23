package com.places.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.places.api.domain.Empresa;
import com.places.api.domain.projections.EmpresaDto;

/**
 * EmpresaRepository es una interfaz que extiende JpaRepository para proporcionar
 * métodos de acceso a datos para la entidad Empresa. Permite realizar operaciones
 * CRUD (Crear, Leer, Actualizar, Eliminar) y consultas personalizadas en la base de datos.
 */
@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
  @Procedure(procedureName = "sp_empresa_buscar")
  List<EmpresaDto> findByCriteria(
    @Param("nombre") String nombre,
    @Param("categoria_id") Short categoriaId
  );
}