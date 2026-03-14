package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.VerificacionCorreo;

@Repository
public interface VerificacionCorreoRepository extends JpaRepository<VerificacionCorreo, Long> {
        VerificacionCorreo findByToken(String token);
}
