package com.places.api.services.interfaces;

import com.places.api.domain.Recomendacion;

public interface RecomendacionService {
    
    Recomendacion crearRecomendacion (Recomendacion recomendacion);

    Recomendacion actualizarAceptada(Long recomendacionId, Boolean aceptada);
}
