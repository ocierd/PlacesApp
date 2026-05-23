package com.places.api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.places.api.domain.Calificacion;
import com.places.api.services.interfaces.CalificacionService;

@RestController
@RequestMapping("/Calificaciones")
public class CalificacionController {

    private final CalificacionService calificacionService;

    public CalificacionController(CalificacionService calificacionService){
        this.calificacionService=calificacionService;
    }
    
    @PostMapping
    public Calificacion crearCalificacion (@RequestBody Calificacion calificacion) {
        return calificacionService.crearCalificacion(calificacion);
    }
}
