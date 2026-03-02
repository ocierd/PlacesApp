package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo_pago") 
public class TipoPago {
  @Id
  @Column(name = "tipo_pago_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Short tipoPagoId;

  @Column(name = "nombre", nullable = false)
  private String nombre;

  public Short getTipoPagoId() {
    return this.tipoPagoId;
  }

  public void setTipoPagoId(Short tipoPagoId) {
    this.tipoPagoId = tipoPagoId;
  }

  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}