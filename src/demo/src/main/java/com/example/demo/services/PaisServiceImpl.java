package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Pais;
import com.example.demo.repository.PaisRepository;
import com.example.demo.services.interfaces.PaisService;

/**
 * Implementación de la interfaz PaisService para manejar las operaciones
 * relacionadas con los países.
 */
@Service
public class PaisServiceImpl implements PaisService {

    /**
     * Repositorio para manejar las operaciones de persistencia relacionadas con los
     * países.
     */
    private final PaisRepository paisRepository;

    /**
     * Constructor para inyectar las dependencias del repositorio de países.
     * 
     * @param paisRepository Repositorio para manejar las operaciones de
     *                       persistencia de países.
     */
    public PaisServiceImpl(PaisRepository paisRepository) {
        this.paisRepository = paisRepository;
    }


    /**
     * Obtiene la lista de países
     */
    @Override
    public List<Pais> obtenerPaises() {
        return paisRepository.findAll();
    }

}
