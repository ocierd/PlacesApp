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
@Table(name = "dia")
@Getter
@Setter
public class Dia {
  @Id
  @Column(name = "dia_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Short diaId; 

  @Column(name = "nombre")
  private String nombre;

}