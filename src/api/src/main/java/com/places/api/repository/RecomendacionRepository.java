package com.places.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.places.api.domain.Recomendacion;

@Repository
public interface RecomendacionRepository extends JpaRepository<Recomendacion,Long> {
    
}
