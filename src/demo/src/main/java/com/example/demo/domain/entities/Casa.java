package com.example.demo.domain.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Casa {

    private Integer numeroPisos;
    private Integer numeroHabitaciones;
    private Integer numeroBanios;
    private Integer numeroGarajes;
    private Integer numeroPuertasExteriores;
    private Double areaConstruida;
    private Double areaTotal;

    private boolean tieneJardin;
    private boolean tienePiscina;
    private String tipoCasa;


    private Short numeroCocinas;



}
