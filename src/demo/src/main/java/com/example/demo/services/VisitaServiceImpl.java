package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Visita;
import com.example.demo.repository.VisitaRepository;
import com.example.demo.services.interfaces.VisitaService;

@Service
public class VisitaServiceImpl implements VisitaService {

    private final VisitaRepository visitaRepository;

    public VisitaServiceImpl(VisitaRepository visitaRepository){
         this.visitaRepository=visitaRepository;
    }
   
    
    @Override
    public Visita crearVisita(Visita visita){
        Visita creada=visitaRepository.save(visita);
        Long vistaId=creada.getVisitaId();
        if (vistaId !=null ) {
            return visitaRepository.findById(vistaId)
            .orElse(null);
            
        }
        return creada;
    }



}
