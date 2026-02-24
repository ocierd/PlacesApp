package com.example.demo.domain;

import java.sql.Time;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "horario")
public class Horario {
  @Id
  @Column(name = "horario_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long horarioId;

  @Column(name = "hora_apertura")
  private Time horaApertura;

  @Column(name = "hora_cierre")
  private Time horaCierre;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "dia_id", insertable = false, updatable = false)
  private Dia dia;

  @Column(name = "dia_id")
  private Short diaId;

  @ManyToOne
  @JoinColumn(name = "sucursal_id")
  @JsonBackReference // ESTO EVITA LA RECURSIÓN INFINITA (Lado inverso)
  private Sucursal sucursal;

  public Long getHorarioId() {
    return this.horarioId;
  }

  public void setHorarioId(Long horarioId) {
    this.horarioId = horarioId;
  }

  public Time getHoraApertura() {
    return this.horaApertura;
  }

  public void setHoraApertura(Time horaApertura) {
    this.horaApertura = horaApertura;
  }

  public Time getHoraCierre() {
    return this.horaCierre;
  }

  public void setHoraCierre(Time horaCierre) {
    this.horaCierre = horaCierre;
  }

  public Short getDiaId() {
    return this.diaId;
  }

  public void setDiaId(Short diaId) {
    this.diaId = diaId;
  }

  public Sucursal getSucursal() {
    return this.sucursal;
  }

  public void setSucursal(Sucursal sucursal) {
    this.sucursal = sucursal;
  }
}