package com.example.demo.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.TipoPago;
import com.example.demo.services.interfaces.TipoPagoService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/tiposPago")
public class TipoPagoController {
  private final TipoPagoService tipoPagoService;

  public TipoPagoController(TipoPagoService tipoPagoService) {
    this.tipoPagoService = tipoPagoService;
  }

  @GetMapping
  public List<TipoPago> getAllTiposPago() {
      return tipoPagoService.getAllTiposPago();
  }
  
  @PostMapping
  public TipoPago crearTipoPago(@RequestBody TipoPago tipoPago) {
      return tipoPagoService.crearTipoPago(tipoPago);
  }
}