package com.places.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.places.api.domain.Dia;
import com.places.api.services.interfaces.DiaService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/dias")
public class DiasController {
  private final DiaService diaService;

  public DiasController(DiaService diaService) {
    this.diaService = diaService;
  }

  @GetMapping()
  public List<Dia> getDias() {
      return diaService.getAllDias();
  }
}