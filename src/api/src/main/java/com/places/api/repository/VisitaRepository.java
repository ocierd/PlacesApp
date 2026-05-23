package com.places.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.places.api.domain.Visita;

@Repository
public interface VisitaRepository extends JpaRepository<Visita,Long> {
    boolean existsByUsuarioIdAndSucursalId(Long usuarioId, Long sucursalId);
}