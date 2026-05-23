package com.places.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.places.api.domain.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {
  
}