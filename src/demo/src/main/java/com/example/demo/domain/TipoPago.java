package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tipo_pago") 
@Setter
@Getter
public class TipoPago {
  @Id
  @Column(name = "tipo_pago_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Short tipoPagoId;

  @Column(name = "nombre", nullable = false)
  private String nombre;

}