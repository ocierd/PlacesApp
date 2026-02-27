package com.example.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Horario;
import com.example.demo.domain.exceptions.NoEncontradoException;
import com.example.demo.domain.exceptions.ValidacionException;
import com.example.demo.services.interfaces.SucursalService;

/**
 * ServicioImpl es una clase de servicio que implementa la lógica de negocio
 * para manejar las operaciones relacionadas con las sucursales. Proporciona
 * métodos para agregar horarios a las suc
 */
@Service
public class ServicioImpl {
    /**
     * Logger
     */
    private static final Logger logger = LoggerFactory.getLogger(ServicioImpl.class);

    /**
     * SucursalService es un servicio que proporciona la lógica de negocio para
     * manejar las operaciones relacionadas con las sucursales.
     */
    private final SucursalService sucursalService;


    /**
     * Constructor de ServicioImpl que inyecta la dependencia de SucursalService.
     * @param sucursalService El servicio de sucursal que se utilizará para manejar las operaciones relacionadas con las sucursales
     */
    public ServicioImpl(SucursalService sucursalService) {
        this.sucursalService = sucursalService;
    }

    /**
     * Agrega un horario a una sucursal específica. Recibe el ID de la sucursal y el horario a agregar. Maneja las excepciones de validación y no encontrado, registrando los errores
     * @param intSucursalId El ID de la sucursal a la que se desea agregar el horario
     * @param horario El horario que se desea agregar a la sucursal
     * @throws ValidacionException Si ocurre un error de validación al agregar el horario a la sucursal
     * @throws NoEncontradoException Si la sucursal con el ID especificado no se encuentra al intentar agregar el horario
     */
    public void agregarHorarioASucursal(Long intSucursalId, Horario horario)
            throws ValidacionException, NoEncontradoException {
        try {
            sucursalService.agregarHorario(intSucursalId, horario);
        } catch (ValidacionException ex) {
            logger.warn("Error de validación al agregar horario a la sucursal con ID: " + intSucursalId + ". Detalles: "
                    + ex.getErrores());
            throw ex; // Re-lanzar la excepción para que sea manejada por el GlobalExceptionHandler
        } catch (NoEncontradoException ex) {
            logger.warn("Sucursal no encontrada al intentar agregar horario. ID de sucursal: " + intSucursalId
                    + ". Detalles: " + ex.getMessage());
            throw ex; // Re-lanzar la excepción para que sea manejada por el GlobalExceptionHandler
        } catch (Exception e) {
            logger.error("Error al agregar horario a la sucursal con ID: " + intSucursalId, e);
            throw e; // Re-lanzar la excepción para que sea manejada por el GlobalExceptionHandler
        }
    }

}
