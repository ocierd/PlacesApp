package com.example.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Empresa;
import com.example.demo.domain.Usuario;
import com.example.demo.domain.projections.EmpresaDto;
import com.example.demo.services.interfaces.EmpresaService;

import io.swagger.v3.oas.annotations.Operation;

/**
 * EmpresaController es un controlador REST que maneja las solicitudes
 * relacionadas con las empresas en la aplicación.
 * Proporciona endpoints para crear nuevas empresas y obtener la lista de todas
 * las empresas.
 */
@RestController
@RequestMapping("/empresas")
public class EmpresaController extends BaseController {

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
        Usuario usuario = this.getCurrentUser();
        empresa.setUsuario(usuario);
        return empresaService.crearEmpresa(empresa);
    }

    @GetMapping("/{id}")
    public Empresa getEmpresaById(@PathVariable Integer id) {
        return empresaService.getEmpresaById(id);
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

    @GetMapping("/buscar")
    @Operation(summary = "Buscar empresas", description = "Busca empresas por nombre.")
    public List<EmpresaDto> buscarEmpresas(@RequestParam String nombre, @RequestParam short categoriaId) {
        return empresaService.buscarEmpresas(nombre, categoriaId);
    }
}