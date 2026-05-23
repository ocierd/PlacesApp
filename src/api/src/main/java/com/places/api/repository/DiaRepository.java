package com.places.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.places.api.domain.Dia;

@Repository
public interface DiaRepository extends JpaRepository<Dia, Short> {
  
}