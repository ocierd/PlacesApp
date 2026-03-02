package com.example.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.repository.SucursalTipoPagoRepository;
import com.example.demo.services.interfaces.SucursalTipoPagoService;

@Service
public class SucursalTipoPagoServiceImpl implements SucursalTipoPagoService {
  private static final Logger logger = LoggerFactory.getLogger(SucursalServiceImpl.class);

  private final SucursalTipoPagoRepository sucursalTipoPagoRepository;

  public SucursalTipoPagoServiceImpl(SucursalTipoPagoRepository sucursalTipoPagoRepository) {
    this.sucursalTipoPagoRepository = sucursalTipoPagoRepository;
  }

  @Override
  public void eliminarSucursalTipoPago(Long sucursalTipoPagoId) {
    try {
      sucursalTipoPagoRepository.deleteById(sucursalTipoPagoId);
    } catch (Exception e) {
      logger.error("Error al eliminar el tipo de pago sucursal con ID {}: {}", sucursalTipoPagoId, e.getMessage(), e);
      throw e;
    }
  }
}