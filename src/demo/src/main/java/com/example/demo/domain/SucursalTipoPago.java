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
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sucursal_tipo_pago")
@Setter
@Getter
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

}