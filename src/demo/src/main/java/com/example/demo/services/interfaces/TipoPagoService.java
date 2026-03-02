package com.example.demo.services.interfaces;

import java.util.List;

import com.example.demo.domain.TipoPago;

public interface TipoPagoService {
  List<TipoPago> getAllTiposPago();

  TipoPago crearTipoPago(TipoPago tipoPago);
}