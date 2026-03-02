package com.example.demo.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.services.interfaces.SucursalTipoPagoService;

@RestController
@RequestMapping("/sucursalTiposPago")
public class SucursalTipoPagoController {
  private final SucursalTipoPagoService sucursalTipoPagoService;

  public SucursalTipoPagoController(SucursalTipoPagoService sucursalTipoPagoService) {
    this.sucursalTipoPagoService = sucursalTipoPagoService;
  }

  @DeleteMapping("/{id}")
  public void eliminarSucursalTipoPago(@PathVariable Long id) {
    sucursalTipoPagoService.eliminarSucursalTipoPago(id);
  }
}