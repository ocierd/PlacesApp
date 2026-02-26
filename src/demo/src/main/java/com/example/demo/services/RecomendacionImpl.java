package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Recomendacion;
import com.example.demo.repository.RecomendacionRepository;
import com.example.demo.services.interfaces.RecomendacionService;

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
