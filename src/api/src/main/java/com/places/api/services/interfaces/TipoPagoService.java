package com.places.api.services.interfaces;

import java.util.List;

import com.places.api.domain.TipoPago;

public interface TipoPagoService {
  List<TipoPago> getAllTiposPago();

  TipoPago crearTipoPago(TipoPago tipoPago);
}