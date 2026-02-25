package com.example.demo.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Calificacion;
import com.example.demo.services.interfaces.CalificacionService;

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
