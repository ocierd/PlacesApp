package com.places.api.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.places.api.domain.Recomendacion;
import com.places.api.services.interfaces.RecomendacionService;

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

