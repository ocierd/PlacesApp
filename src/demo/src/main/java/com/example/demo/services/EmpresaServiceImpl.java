package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Empresa;
import com.example.demo.repository.EmpresaRepository;
import com.example.demo.services.interfaces.EmpresaService;

/**
 * EmpresaServiceImpl es una clase que implementa la interfaz EmpresaService y
 * proporciona la lógica de negocio para manejar las empresas en la aplicación.
 */
@Service
public class EmpresaServiceImpl implements EmpresaService {

    /**
     * EmpresaRepository es un repositorio que proporciona métodos de acceso a datos para la entidad Empresa. Permite realizar operaciones CRUD (Crear, Leer,
     * Actualizar, Eliminar) y consultas personalizadas en la base de datos.
     */
    private EmpresaRepository empresaRepository;

    /**
     * Constructor de EmpresaServiceImpl que inyecta la dependencia de EmpresaRepository.
     * @param empresaRepository El repositorio de empresa que se utilizará para manejar las operaciones relacionadas con las empresas
     */
    public EmpresaServiceImpl(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    /**
     * Obtiene una lista de todas las empresas disponibles.
     * @return Lista de todas las empresas
     */
    @Override
    public List<Empresa> getAllEmpresas() {
        List<Empresa> empresas = empresaRepository.findAll();
        return empresas;
    }

    /**
     * Crea una nueva empresa en la base de datos. Recibe un objeto Empresa con los datos de la empresa a crear y devuelve la empresa creada.
     * @param empresa El objeto Empresa que se desea crear
     * @return La empresa creada
     */
    @Override
    public Empresa crearEmpresa(Empresa empresa) {
        Empresa creada = empresaRepository.save(empresa);
        return creada;
    }

}
