package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Empresa;

/**
 * EmpresaRepository es una interfaz que extiende JpaRepository para proporcionar
 * métodos de acceso a datos para la entidad Empresa. Permite realizar operaciones
 * CRUD (Crear, Leer, Actualizar, Eliminar) y consultas personalizadas en la base de datos.
 */
@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

}
