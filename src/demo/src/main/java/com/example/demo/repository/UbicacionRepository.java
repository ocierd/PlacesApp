package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Ubicacion;

@Repository
public interface UbicacionRepository extends JpaRepository<Ubicacion,Long> {
    
}
