package com.example.demo.controllers;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Visita;
import com.example.demo.services.interfaces.VisitaService;

@RestController
@RequestMapping("/visitas")
public class VisitaController {

    private final VisitaService visitaService;

    public VisitaController(VisitaService visitaService)
    {
        this.visitaService=visitaService;
    }

    @PostMapping
    public Visita crearVisita(@RequestBody Visita visita){
        return visitaService.crearVisita(visita);

    }



}
