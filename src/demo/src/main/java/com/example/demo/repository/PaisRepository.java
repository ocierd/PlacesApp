package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Pais;

/**
 * Representa el repositorio de la entidad País.
 */
@Repository
public interface PaisRepository extends JpaRepository<Pais,Short> {
    
}
