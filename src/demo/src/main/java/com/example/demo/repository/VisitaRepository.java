package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Visita;

@Repository
public interface VisitaRepository extends JpaRepository<Visita,Long> {
    boolean existsByUsuarioIdAndSucursalId(Long usuarioId, Long sucursalId);
}