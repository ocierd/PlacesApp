package com.places.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.places.api.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    Usuario findByUsername(String username);
    
}
