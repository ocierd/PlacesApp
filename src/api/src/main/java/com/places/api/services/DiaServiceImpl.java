package com.places.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.places.api.domain.Dia;
import com.places.api.repository.DiaRepository;
import com.places.api.services.interfaces.DiaService;

@Service
public class DiaServiceImpl implements DiaService {
  private final DiaRepository diaRepository;

  public DiaServiceImpl(DiaRepository diaRepository) {
    this.diaRepository = diaRepository;
  }

  public Dia crearDia(Dia dia) {
    return diaRepository.save(dia);
  }

  public List<Dia> getAllDias() {
    return diaRepository.findAll();
  }
}