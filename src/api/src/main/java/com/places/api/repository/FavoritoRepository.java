package com.places.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.places.api.domain.Favorito;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito,Long> {
    
}
