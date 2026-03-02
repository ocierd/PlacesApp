package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sucursal_tipo_pago")
public class SucursalTipoPago {
  @Id
  @Column(name = "sucursal_tipo_pago_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long sucursalTipoPagoId;

  @ManyToOne
  @JoinColumn(name = "sucursal_id")
  @JsonBackReference // ESTO EVITA LA RECURSIÓN INFINITA (Lado inverso)
  private Sucursal sucursal;

  @ManyToOne
  @JoinColumn(name = "tipo_pago_id", insertable = false, updatable = false)
  private TipoPago tipoPago;

  public Long getSucursalTipoPagoId() {
    return this.sucursalTipoPagoId;
  }

  public void setSucursalTipoPagoId(Long sucursalTipoPagoId) {
    this.sucursalTipoPagoId = sucursalTipoPagoId;
  }

  public Sucursal getSucursal() {
    return this.sucursal;
  }

  public void setSucursal(Sucursal sucursal) {
    this.sucursal = sucursal;
  }

  public TipoPago getTipoPago() {
    return this.tipoPago;
  }

  public void setTipoPago(TipoPago tipoPago) {
    this.tipoPago = tipoPago;
  }
}