package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Telefono;

@Repository
public interface TelefonoRepository extends JpaRepository<Telefono,Long> {
    
    Optional<Telefono> findByNumeroAndActivoTrue(String numero);
}
