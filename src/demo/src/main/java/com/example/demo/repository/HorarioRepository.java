package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Horario;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {
  
}