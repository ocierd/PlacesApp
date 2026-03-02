package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.TipoPago;
import com.example.demo.repository.TipoPagoRepository;
import com.example.demo.services.interfaces.TipoPagoService;

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