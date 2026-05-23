package com.places.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.places.api.domain.Pais;
import com.places.api.services.interfaces.PaisService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Controlador para manejar las operaciones relacionadas con los países.
 */
@RestController
@RequestMapping("/paises")
@Tag(name = "Paises", description = "Endpoints para manejar las operaciones relacionadas con los países, como obtener la lista de países disponibles.")
public class PaisController extends BaseController {

    /**
     * Provee acceso a métodos para manejar las oepraciones relacionadas con los
     * países, como obtener la lista de países disponibles.
     */
    private final PaisService paisService;

    /**
     * Constructor para inyectar las dependencias del servicio de países.
     * 
     * @param paisService Provee acceso a métodos para manejar las oepraciones
     *                    relacionadas con los países, como obtener la lista de
     *                    países disponibles.
     */
    public PaisController(PaisService paisService) {
        this.paisService = paisService;
    }

    /**
     * Obtiene la lista de países
     * 
     * @return La lista de países disponibles en el sistema.
     */
    @GetMapping
    @Operation(summary = "Obtener países", description = "Obtiene la lista de países disponibles en el sistema.")
    public List<Pais> obtenerPaises() {
        return paisService.obtenerPaises();
    }

    /**
     * Busca países por nombre
     * @param nombre El nombre o parte del nombre del país a buscar
     * @return La lista de países que coinciden con el criterio de búsqueda. Si no se encuentra ningún país, devuelve una lista vacía.
     */
    @GetMapping("/buscar")
    @Operation(summary = "Buscar países", description = "Busca países por nombre.")
    public List<Pais> buscarPaises(@RequestParam String nombre) {
        return paisService.buscarPaises(nombre);
    }

}
