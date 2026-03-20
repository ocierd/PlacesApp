package com.example.demo.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Telefono;
import com.example.demo.domain.Usuario;
import com.example.demo.domain.dto.TelefonoCreacionCurrentUserDto;
import com.example.demo.domain.exceptions.NoEncontradoException;
import com.example.demo.domain.exceptions.ValidacionException;
import com.example.demo.services.interfaces.TelefonoService;
import com.example.demo.services.interfaces.VerificacionTelefonoService;

/**
 * Controlador para manejar las operaciones relacionadas con los teléfonos de
 * los usuarios. Este controlador se encarga de recibir las solicitudes HTTP
 * relacionadas con los teléfonos, como la creación, actualización y eliminación
 * de teléfonos, y delegar la lógica de negocio a los servicios
 * correspondientes. Además, este controlador se encarga de obtener el usuario
 * actual autenticado para asociar los teléfonos a dicho usuario.
 */
@RestController
@RequestMapping("/telefonos")
public class TelefonoController extends BaseController {

    /**
     * Servicio para la verificación de teléfono. Este servicio se encarga de
     * manejar la lógica de negocio relacionada con la verificación de teléfonos,
     * como el
     * envío de códigos
     */
    private final TelefonoService telefonoService;

    /**
     * Servicio para la verificación de teléfono. Este servicio se encarga de
     * manejar la lógica de negocio relacionada con la verificación de teléfonos,
     * como el envío de códigos de verificación y la validación de los mismos.
     */
    private final VerificacionTelefonoService verificacionTelefonoService;


    
    public TelefonoController(TelefonoService telefonoService,
            VerificacionTelefonoService verificacionTelefonoService) {
        this.telefonoService = telefonoService;
        this.verificacionTelefonoService = verificacionTelefonoService;
    }

    /**
     * Agrega un nuevo teléfono para el usuario actual. Este método se encarga de
     * validar el número de teléfono, crear una nueva entidad de teléfono y
     * asociarla al usuario actual.
     *
     * @param telefono El DTO que contiene la información del teléfono que se desea
     *                 agregar
     * @return La entidad de teléfono creada y asociada al usuario
     * @throws ValidacionException Si ocurre un error de validación durante la
     *                             creación del teléfono, como un formato de número
     *                             inválido o un número ya registrado
     */
    @PostMapping("current-user")
    public Telefono agregarTelefono(@RequestBody TelefonoCreacionCurrentUserDto telefono)
            throws ValidacionException {
        Usuario u = super.getCurrentUser();
        return telefonoService.agregarTelefono(telefono, u.getUsuarioId());
    }

    /**
     * Obtiene la lista de teléfonos asociados al usuario actual. Este método se
     * encarga de obtener el usuario actual autenticado y luego utilizar el servicio
     * de teléfonos para obtener la lista de teléfonos asociados a dicho usuario.
     * 
     * @return Una lista de teléfonos asociados al usuario actual, o una lista vacía
     *         si no se encuentran teléfonos asociados con el usuario actual
     */
    @GetMapping("current-user")
    public List<Telefono> obtenerTelefonos() {
        Usuario u = super.getCurrentUser();
        return telefonoService.obtenerTelefonos(u.getUsuarioId());
    }

    /**
     * Envía un código de verificación al número de teléfono del usuario. Este
     * método se encarga de validar si el usuario ha rebasado la cantidad de tokens
     * que se le pueden enviar.
     * 
     * @throws ValidacionException Si el usuario ha rebasado la cantidad de tokens
     *                             que se le pueden enviar o si ocurre algún error
     *                             al enviar el código de verificación
     */
    @GetMapping("enviar-codigo-verificacion")
    public void enviarCodigoVerificacion(@RequestParam Long telefonoId)
            throws ValidacionException {
        Usuario u = super.getCurrentUser();
        verificacionTelefonoService.enviarCodigoVerificacion(u, telefonoId);
    }

    /**
     * Verifica el código de verificación enviado al número de teléfono del usuario.
     * Este método se encarga de validar si el código es correcto y si el teléfono
     * puede ser verificado.
     * 
     * @param codigo     El código de verificación enviado al número de teléfono del
     *                   usuario
     * @param telefonoId El ID del teléfono que se desea verificar
     * 
     * @throws ValidacionException   Si el código es incorrecto o si ocurre algún
     *                               error al verificar el teléfono
     * @throws NoEncontradoException Si el teléfono no se encuentra
     */
    @GetMapping("verificacion-codigo")
    public void verificarTelefono(@RequestParam String codigo, @RequestParam Long telefonoId)
            throws ValidacionException, NoEncontradoException {
        verificacionTelefonoService.verificarTelefono(codigo, telefonoId);
    }
}
