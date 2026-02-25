package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Calificacion;
import com.example.demo.repository.CalificacionRepository;
import com.example.demo.repository.VisitaRepository;
import com.example.demo.services.interfaces.CalificacionService;

@Service
public class CalificacionServiceImpl implements CalificacionService {
    private final CalificacionRepository calificacionRepository;

    private final VisitaRepository visitaRepository;

    public CalificacionServiceImpl(CalificacionRepository calificacionRepository, VisitaRepository visitaRepository) {
        this.calificacionRepository = calificacionRepository;
        this.visitaRepository = visitaRepository;
    }

    @Override
    public Calificacion crearCalificacion(Calificacion calificacion) {

        boolean haVisitado = visitaRepository.existsByUsuarioIdAndSucursalId(calificacion.getUsuarioId(),
                calificacion.getSucursalId());

        boolean yaCalifico = visitaRepository.existsByUsuarioIdAndSucursalId(calificacion.getUsuarioId(),
                calificacion.getSucursalId());
        if (!haVisitado || yaCalifico) {
            return null;
        }

        Calificacion creada = calificacionRepository.save(calificacion);
        Long calificacionId = creada.getCalificacionId();

        if (calificacionId != null) {
            return calificacionRepository.findById(calificacionId)
                    .orElse(null);
        }
        return creada;

    }

}
