package com.example.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Empresa;
import com.example.demo.services.interfaces.EmpresaService;

/**
 * EmpresaController es un controlador REST que maneja las solicitudes
 * relacionadas con las empresas en la aplicación.
 * Proporciona endpoints para crear nuevas empresas y obtener la lista de todas
 * las empresas.
 */
@RestController
@RequestMapping("/empresas")
public class EmpresaController {

    /**
     * EmpresaService es un servicio que proporciona la lógica de negocio para
     * manejar las operaciones relacionadas con las empresas.
     */
    private EmpresaService empresaService;

    /**
     * Constructor de EmpresaController que inyecta la dependencia de
     * EmpresaService.
     * 
     * @param empresaService El servicio de empresa que se utilizará para manejar
     *                       las operaciones relacionadas con las empresas
     */
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    /**
     * Endpoint para crear una nueva empresa. Recibe un objeto Empresa en el cuerpo
     * de la solicitud y devuelve la empresa creada.
     * 
     * @param empresa El objeto Empresa que se desea crear
     * @return La empresa creada
     */
    @PostMapping
    public Empresa crearEmpresa(@RequestBody Empresa empresa) {
        return empresaService.crearEmpresa(empresa);
    }

    /**
     * Endpoint para obtener la lista de todas las empresas. Devuelve una lista de
     * todas las empresas disponibles en la aplicación.
     * 
     * @return Lista de todas las empresas
     */
    @GetMapping
    public List<Empresa> getEmpresas() {
        return empresaService.getAllEmpresas();
    }

}
