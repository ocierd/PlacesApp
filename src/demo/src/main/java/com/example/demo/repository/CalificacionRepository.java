package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.domain.Calificacion;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion,Long> {

       boolean existsByUsuarioIdAndSucursalId(Long usuarioId,Long sucursalId);
} 