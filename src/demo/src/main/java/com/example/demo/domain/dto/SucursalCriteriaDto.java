package com.example.demo.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SucursalCriteriaDto {
  private String criterioBusqueda;
  private UbicacionDto ubicacion;
  private double distanciaKms;
  private Short tipoPagoId;

}