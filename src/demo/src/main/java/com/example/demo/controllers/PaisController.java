package com.example.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Pais;
import com.example.demo.services.interfaces.PaisService;

/**
 * Controlador para manejar las operaciones relacionadas con los países.
 */
@RestController
@RequestMapping("/paises")
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
     * @return La lista de países disponibles en el sistema.
    */
    @GetMapping
    public List<Pais> obtenerPaises() {
        return paisService.obtenerPaises();
    }

}
