package com.places.api.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.places.api.domain.Pais;
import com.places.api.repository.PaisRepository;
import com.places.api.services.interfaces.PaisService;

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

    /**
     * Busca países por nombre
     * 
     * @param nombre El nombre o parte del nombre del país a buscar
     * @return La lista de países que coinciden con el criterio de búsqueda. Si no
     *         se encuentra ningún país, devuelve una lista vacía.
     */
    @Transactional(readOnly = true)
    @Override
    public List<Pais> buscarPaises(String nombre) {
        return paisRepository.buscarPaises(nombre);
    }

}
