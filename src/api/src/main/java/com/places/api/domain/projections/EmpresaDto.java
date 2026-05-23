package com.places.api.domain.projections;

import jakarta.persistence.Column;

public interface EmpresaDto {
  @Column(name = "empresaId") 
  Integer getEmpresaId();

  @Column(name = "empresa") 
  String getEmpresa();

  @Column(name = "categoriaId") 
  short getCategoriaId();

  @Column(name = "categoria") 
  String getCategoria();
}