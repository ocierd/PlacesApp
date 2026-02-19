package com.example.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Sucursal;
import com.example.demo.domain.dto.SucursalDto;
import com.example.demo.services.interfaces.SucursalService;

/**
 * SucursalController es un controlador REST que maneja las solicitudes
 * relacionadas con las sucursales en la aplicación. Proporciona endpoints para
 * crear nuevas sucursales y obtener la lista de todas las sucursales.
 */
@RestController
@RequestMapping("/sucursales")
public class SucursalController {

    /**
     * SucursalService es un servicio que proporciona la lógica de negocio para manejar las operaciones relacionadas con las sucursales.
     */
    private final SucursalService sucursalService;

    /**
     * Constructor de SucursalController que inyecta la dependencia de SucursalService.
     * @param sucursalService El servicio de sucursal que se utilizará para manejar las operaciones relacionadas con las sucursales
     */
    public SucursalController(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    /**
     * Endpoint para crear una nueva sucursal. Recibe un objeto Sucursal en el cuerpo de la solicitud y devuelve la sucursal creada.
     * @param sucursal El objeto Sucursal que se desea crear
     * @return La sucursal creada
     */
    @PostMapping
    public Sucursal crearSucursal(@RequestBody Sucursal sucursal) {
        return sucursalService.crearSucursal(sucursal);
    }


    /**
     * Endpoint para crear una nueva sucursal a partir de un objeto SucursalDto. Recibe un objeto SucursalDto en el cuerpo de la solicitud y devuelve la sucursal creada. Este endpoint es útil para recibir datos de la sucursal desde el cliente en formato DTO (Data Transfer Object) y luego convertirlo a una entidad Sucursal para guardarlo en la base de datos.
     * @param sucursal El objeto SucursalDto que se desea crear
     * @return La sucursal creada
     */
    @PostMapping("/nueva-sucursal")
    public Sucursal crearSucursal(@RequestBody SucursalDto sucursal) {
        return sucursalService.crearSucursal(sucursal);
    }


    /**
     * Endpoint para obtener la lista de todas las sucursales. Devuelve una lista de todas las sucursales disponibles en la aplicación.
     * @return Lista de todas las sucursales
     */
    @GetMapping
    public List<Sucursal> getSucursales() {
        return sucursalService.getAllSucursales();
    }
}
