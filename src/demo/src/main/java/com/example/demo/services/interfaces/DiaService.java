package com.example.demo.services.interfaces;

import java.util.List;

import com.example.demo.domain.Dia;

public interface DiaService {
  Dia crearDia(Dia dia);

  List<Dia> getAllDias();
}