package com.places.api.services.interfaces;

import java.util.List;

import com.places.api.domain.Dia;

public interface DiaService {
  Dia crearDia(Dia dia);

  List<Dia> getAllDias();
}