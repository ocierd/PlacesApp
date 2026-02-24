package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Dia;
import com.example.demo.repository.DiaRepository;
import com.example.demo.services.interfaces.DiaService;

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