package com.places.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.places.api.domain.TipoPago;
import com.places.api.repository.TipoPagoRepository;
import com.places.api.services.interfaces.TipoPagoService;

@Service
public class TipoPagoServiceImpl implements TipoPagoService {
  private final TipoPagoRepository tipoPagoRepository;

  public TipoPagoServiceImpl(TipoPagoRepository tipoPagoRepository) {
    this.tipoPagoRepository = tipoPagoRepository;
  }

  @Override
  public List<TipoPago> getAllTiposPago() {
    return tipoPagoRepository.findAll();
  }

  @Override
  public TipoPago crearTipoPago(TipoPago tipoPago) {
    return tipoPagoRepository.save(tipoPago);
  }
}