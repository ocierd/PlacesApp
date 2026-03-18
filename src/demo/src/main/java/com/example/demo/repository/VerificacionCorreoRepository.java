package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.VerificacionCorreo;

@Repository
public interface VerificacionCorreoRepository extends JpaRepository<VerificacionCorreo, Long> {
        VerificacionCorreo findByToken(UUID token);

        Integer countByCorreoIdAndFechaConfirmacionIsNotNull(Long correoId);

        Integer countByCorreoIdAndFechaConfirmacionIsNull(Long correoId);

        Integer countByCorreoIdAndFechaExpiracionAfter(Long correoId, LocalDateTime fecha);
}