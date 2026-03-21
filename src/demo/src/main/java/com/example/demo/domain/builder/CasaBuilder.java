package com.example.demo.domain.builder;

import com.example.demo.domain.entities.Casa;

public class CasaBuilder {

    Integer numeroPisos = 1; // Obligatorio
    Integer numeroHabitaciones; // Obligatorio
    Integer numeroBanios; // Obligatorio
    Integer numeroGarajes;
    Integer numeroPuertasExteriores; // Obligatorio
    Double areaConstruida;
    Double areaTotal;
    boolean tieneJardin = false;
    boolean tienePiscina = false;
    String tipoCasa = "Casa Sola"; // Ejemplo de atributo adicional


     Short numeroCocinas=0; // Ejemplo de atributo adicional

    public CasaBuilder buildNumeroPisos(Integer numeroPisos) {
        this.numeroPisos = numeroPisos;
        return this;
    }

    public CasaBuilder buildNumeroHabitaciones(Integer numeroHabitaciones) {
        this.numeroHabitaciones = numeroHabitaciones;
        return this;
    }

    public CasaBuilder buildNumeroBanios(Integer numeroBanios) {
        this.numeroBanios = numeroBanios;
        return this;
    }

    public CasaBuilder buildNumeroGarajes(Integer numeroGarajes) {
        this.numeroGarajes = numeroGarajes;
        return this;
    }

    public CasaBuilder buildAreaConstruida(Double areaConstruida) {
        this.areaConstruida = areaConstruida;
        return this;
    }

    public CasaBuilder buildAreaTotal(Double areaTotal) {
        this.areaTotal = areaTotal;
        return this;
    }

    public CasaBuilder buildTieneJardin(boolean tieneJardin) {
        this.tieneJardin = tieneJardin;
        return this;
    }

    public CasaBuilder buildTienePiscina(boolean tienePiscina) {
        this.tienePiscina = tienePiscina;
        return this;
    }

    public CasaBuilder buildTipoCasa(String tipoCasa) {
        this.tipoCasa = tipoCasa;
        return this;
    }

    public CasaBuilder buildNumeroPuertasExteriores(Integer numeroPuertasExteriores) {
        this.numeroPuertasExteriores = numeroPuertasExteriores;
        return this;
    }


    public CasaBuilder buildNumeroCocinas(Short numeroCocinas) {
        this.numeroCocinas = numeroCocinas;
        return this;
    }

    public Casa build() {
        if(numeroHabitaciones == null || numeroBanios == null || numeroPuertasExteriores == null) {
            throw new IllegalStateException("Los campos numeroHabitaciones, numeroBanios y numeroPuertasExteriores son obligatorios");
        }

        if (numeroPisos < 1) {
            throw new IllegalStateException("El número de pisos debe ser al menos 1");
        }

        if (numeroHabitaciones < 0 || numeroBanios < 0 || numeroGarajes != null && numeroGarajes < 0) {
            throw new IllegalStateException("El número de habitaciones, baños y garajes no puede ser negativo");
            
        }

        Casa casa = new Casa();
        casa.setNumeroPisos(this.numeroPisos);
        casa.setNumeroHabitaciones(this.numeroHabitaciones);
        casa.setNumeroBanios(this.numeroBanios);
        casa.setNumeroGarajes(this.numeroGarajes);
        casa.setAreaConstruida(this.areaConstruida);
        casa.setAreaTotal(this.areaTotal);
        casa.setTieneJardin(this.tieneJardin);
        casa.setTienePiscina(this.tienePiscina);
        casa.setTipoCasa(this.tipoCasa);
        casa.setNumeroPuertasExteriores(this.numeroPuertasExteriores);
        casa.setNumeroCocinas(this.numeroCocinas);
        // Aquí podrías agregar lógica adicional para validar o configurar la casa antes de devolverla
        return casa;
    }
}
