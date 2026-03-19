package com.example.demo.domain;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "horario")
@Getter
@Setter
public class Horario {
  @Id
  @Column(name = "horario_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long horarioId;

  @Column(name = "hora_apertura")
  private LocalTime horaApertura;

  @Column(name = "hora_cierre")
  private LocalTime horaCierre;

  @OneToOne
  @JoinColumn(name = "dia_id", insertable = false, updatable = false)
  private Dia dia;

  @Column(name = "dia_id")
  private Short diaId;

  @ManyToOne
  @JoinColumn(name = "sucursal_id")
  @JsonBackReference // ESTO EVITA LA RECURSIÓN INFINITA (Lado inverso)
  private Sucursal sucursal;


}