package com.example.demo.domain.projections;

public interface SucursalSummary {
    // sucursal_id
    Long getSucursalId();

    // nombre
    String getNombre();

    // empresa_id
    Integer getEmpresaId();

    // empresaNombre
    String getEmpresaNombre();

    // categoria_id
    short getCategoriaId();

    // categoriaNombre
    String getCategoriaNombre();

    // Distancia en kilómetros
    Double getDistanciaKms();
}