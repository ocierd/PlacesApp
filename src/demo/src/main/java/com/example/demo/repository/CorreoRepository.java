package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Correo;

@Repository
public interface CorreoRepository extends JpaRepository<Correo,Long> {
    
    Optional<Correo> findByCorreoElectronicoAndActivoTrue(String correoElectronico);
    
}
