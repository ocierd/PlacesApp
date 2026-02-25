package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Sucursal;
import com.example.demo.domain.Visita;
import com.example.demo.repository.SucursalRepository;
import com.example.demo.repository.VisitaRepository;
import com.example.demo.services.interfaces.VisitaService;

@Service
public class VisitaServiceImpl implements VisitaService {

    private final VisitaRepository visitaRepository;
    private final SucursalRepository sucursalRepository;

    public VisitaServiceImpl(VisitaRepository visitaRepository, SucursalRepository sucursalRepository){
         this.visitaRepository=visitaRepository;
         this.sucursalRepository=sucursalRepository;
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
