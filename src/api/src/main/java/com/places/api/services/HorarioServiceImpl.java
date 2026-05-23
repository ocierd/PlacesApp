package com.places.api.services;

import org.springframework.stereotype.Service;

import com.places.api.domain.Horario;
import com.places.api.repository.HorarioRepository;
import com.places.api.services.interfaces.HorarioService;

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