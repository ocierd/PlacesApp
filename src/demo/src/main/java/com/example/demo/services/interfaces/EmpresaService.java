package com.example.demo.services.interfaces;

import java.util.List;

import com.example.demo.domain.Empresa;

/**
 * EmpresaService es una interfaz que define los métodos de negocio relacionados con las empresas.
 * Proporciona operaciones para obtener todas las empresas y crear nuevas empresas.
 */
public interface EmpresaService {
    
    /**
     * Obtiene una lista de todas las empresas disponibles.
     * @return Lista de todas las empresas
     */
    List<Empresa> getAllEmpresas();
    
    /**
     * Crea una nueva empresa en la base de datos. Recibe un objeto Empresa con los datos de la empresa a crear y devuelve la empresa creada.
     * @param empresa El objeto Empresa que se desea crear
     * @return La empresa creada
     */
    Empresa crearEmpresa(Empresa empresa);

}
