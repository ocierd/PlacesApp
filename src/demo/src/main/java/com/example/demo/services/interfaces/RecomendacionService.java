package com.example.demo.services.interfaces;

import com.example.demo.domain.Recomendacion;

public interface RecomendacionService {
    
    Recomendacion crearRecomendacion (Recomendacion recomendacion);

    Recomendacion actualizarAceptada(Long recomendacionId, Boolean aceptada);
}
