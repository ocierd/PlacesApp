package com.places.api.services.interfaces;

import com.places.api.domain.Horario;

public interface HorarioService {
  Horario crearHorario(Horario horario);

  //List<Horario> getBySucursalId(Long Id);
}