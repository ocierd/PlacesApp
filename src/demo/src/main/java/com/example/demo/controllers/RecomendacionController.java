package com.example.demo.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Recomendacion;
import com.example.demo.services.interfaces.RecomendacionService;

@RestController
@RequestMapping("/recomendaciones")
public class RecomendacionController {

    private final RecomendacionService recomendacionService;

    public RecomendacionController(RecomendacionService recomendacionService) 
    {
            this.recomendacionService=recomendacionService;
    }  

    @PostMapping
    public Recomendacion crearRecomendacion (@RequestBody Recomendacion recomendacion){
        return recomendacionService.crearRecomendacion(recomendacion);
    }
}

