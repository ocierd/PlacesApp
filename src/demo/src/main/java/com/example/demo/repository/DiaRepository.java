package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Dia;

@Repository
public interface DiaRepository extends JpaRepository<Dia, Short> {
  
}