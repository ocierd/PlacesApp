package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Horario;
import com.example.demo.repository.HorarioRepository;
import com.example.demo.services.interfaces.HorarioService;

@Service
public class HorarioServiceImpl implements HorarioService {
  private final HorarioRepository horarioRepository;

  public HorarioServiceImpl(HorarioRepository horarioRepository) {
    this.horarioRepository = horarioRepository;
  }

  public Horario crearHorario(Horario horario) {
    return horarioRepository.save(horario);
  }
}