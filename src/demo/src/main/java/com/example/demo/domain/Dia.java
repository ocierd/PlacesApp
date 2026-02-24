package com.example.demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "dia")
public class Dia {
  @Id
  @Column(name = "dia_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Short diaId; 

  @Column(name = "nombre")
  private String nombre;

  public Short getDiaId() {
    return this.diaId;
  }

  public void setDiaId(Short diaId) {
    this.diaId = diaId;
  }

  public String getNombre() {
    return this.nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
}