package com.places.api.services;

import org.springframework.stereotype.Service;

import com.places.api.domain.Recomendacion;
import com.places.api.repository.RecomendacionRepository;
import com.places.api.services.interfaces.RecomendacionService;

@Service
public class RecomendacionImpl implements RecomendacionService {
    private final RecomendacionRepository recomendacionRepository;

    public RecomendacionImpl(RecomendacionRepository recomendacionRepository) {
        this.recomendacionRepository = recomendacionRepository;
    }

    @Override
    public Recomendacion crearRecomendacion(Recomendacion recomendacion) {
        
        Recomendacion creada = recomendacionRepository.save(recomendacion);
        Long recomendacionId = creada.getRecomendacioId();
        if (recomendacionId != null) {
            return recomendacionRepository.findById(recomendacionId)
                    .orElse(null);
        }
        return creada;
    }

    @Override
    public Recomendacion actualizarAceptada(Long recomendacionId, Boolean aceptada) {
        Recomendacion recomendacion = recomendacionRepository.findById(recomendacionId)
                .orElse(null);
        if (recomendacion != null) {
            recomendacion.setAceptada(aceptada);
            return recomendacionRepository.save(recomendacion);
        }
        return null;
    }
}
