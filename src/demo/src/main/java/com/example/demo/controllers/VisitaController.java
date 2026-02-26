package com.example.demo.controllers;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Visita;
import com.example.demo.services.interfaces.VisitaService;

/**
 * VisitaController es un controlador REST que maneja las solicitudes
 * relacionadas con las visitas en la aplicación.
 */
@RestController
@RequestMapping("/visitas")
public class VisitaController {

    private final VisitaService visitaService;

    public VisitaController(VisitaService visitaService) {
        this.visitaService = visitaService;
    }

    /**
     * Crea una nueva visita en la base de datos. Recibe un objeto Visita con los
     * 
     * @param visita El objeto Visita que se desea crear
     * @return La visita creada
     */
    @PostMapping
    public Visita crearVisita(@RequestBody Visita visita) {
        return visitaService.crearVisita(visita);

    }

    /**
     * Actualiza el estado de una visita a "visitado". Recibe el ID de la visita a
     * actualizar y devuelve la visita actualizada con el nuevo estado.
     * 
     * @param visitaId El ID de la visita que se desea actualizar
     * @return La visita actualizada con el nuevo estado "visitado"
     */
    @PatchMapping("/visitado")
    public Visita actualizarVisitado(@RequestParam Long visitaId) {
        return visitaService.actualizarVisitado(visitaId);

    }

}
