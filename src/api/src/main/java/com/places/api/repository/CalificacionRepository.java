package com.places.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.places.api.domain.Calificacion;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion,Long> {

       boolean existsByUsuarioIdAndSucursalId(Long usuarioId,Long sucursalId);
} 