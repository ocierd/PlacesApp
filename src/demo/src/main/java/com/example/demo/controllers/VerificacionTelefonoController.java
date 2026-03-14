package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Usuario;
import com.example.demo.domain.exceptions.ValidacionException;
import com.example.demo.services.interfaces.VerificacionTelefonoService;

/**
 * Controlador para la verificación de teléfono.
 */
@RestController
@RequestMapping("/verificacion-telefono")
public class VerificacionTelefonoController extends BaseController {

    private final VerificacionTelefonoService verificacionTelefonoService;


    public VerificacionTelefonoController(VerificacionTelefonoService verificacionTelefonoService) {
        this.verificacionTelefonoService = verificacionTelefonoService;
    }

    /**
     * Envía un código de verificación al número de teléfono del usuario. Este método se encarga de validar si el usuario ha rebasado la cantidad de tokens que se le pueden enviar.
     * @throws ValidacionException Si el usuario ha rebasado la cantidad de tokens que se le pueden enviar o si ocurre algún error al enviar el código de verificación
     */
    @GetMapping("enviar-codigo-verificacion")
    public void enviarCodigoVerificacion() throws ValidacionException {
        Usuario u = super.getCurrentUser();
        verificacionTelefonoService.enviarCodigoVerificacion(u);
    }

}
