package com.example.demo.domain.dto;

public class SucursalCriteriaDto {
  private String criterioBusqueda;
  private UbicacionDto ubicacion;
  private double distanciaKms;

  public String getCriterioBusqueda() {
    return this.criterioBusqueda;
  }

  public void setCriterioBusqueda(String criterioBusqueda) {
    this.criterioBusqueda = criterioBusqueda;
  }

  public UbicacionDto getUbicacion() {
    return this.ubicacion;
  }

  public void setUbicacion(UbicacionDto ubicacion) {
    this.ubicacion = ubicacion;
  }

  public double getDistanciaKms() {
    return this.distanciaKms;
  }

  public void setDistanciaKms(double distanciaKms) {
    this.distanciaKms = distanciaKms;
  }
}