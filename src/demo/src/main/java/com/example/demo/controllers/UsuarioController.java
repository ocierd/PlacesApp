package com.example.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Usuario;
import com.example.demo.domain.dto.UsuarioRegistroDto;
import com.example.demo.domain.exceptions.ValidacionException;
import com.example.demo.services.interfaces.UsuarioService;

/**
 * Controlador REST encargado de manejar las solicitudes relacionadas con los
 * usuarios. Proporciona endpoints para crear nuevos usuarios, obtener
 * información del usuario actual autenticado y realizar pruebas de
 * codificación. Utiliza el servicio de usuario (UsuarioService) para realizar
 * la lógica de negocio relacionada con los usuarios.
 */
@RestController
@RequestMapping("/usuarios")
public class UsuarioController extends BaseController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * Maneja las solicitudes POST al endpoint /usuarios para crear un nuevo
     * usuario. Recibe un objeto UsuarioRegistroDto en el cuerpo de la solicitud, y devuelve el
     * usuario creado con su información actualizada. Utiliza el servicio de usuario
     * (UsuarioService) para realizar la lógica de creación del usuario, incluyendo
     * la validación de datos y el manejo de contraseñas.
     * 
     * @param usuario El objeto UsuarioRegistroDto que contiene la información del usuario a
     *                crear, incluyendo su nombre de usuario y contraseña sin
     *                codificar.
     * @return El Usuario creado, con su contraseña codificada y su ID asignado por
     *         la base de datos. Si el proceso de creación falla, devuelve null.
     */
    @PostMapping
    public Usuario crearUsuario(@RequestBody UsuarioRegistroDto usuario)  throws ValidacionException {
        return usuarioService.crearUsuario(usuario);
    }

    /**
     * Maneja las solicitudes GET al endpoint /usuarios/me para obtener la
     * información del usuario actual autenticado. Devuelve un objeto Usuario con
     * los detalles del usuario autenticado, como su nombre de usuario y otros
     * atributos relevantes. Este endpoint requiere que el usuario esté autenticado
     * para acceder a su propia información.
     * 
     * @return El Usuario actual autenticado, con su información relevante. Si el
     *         usuario no está autenticado, se devolverá una respuesta de error
     *         adecuada.
     */
    @GetMapping("/me")
    public Usuario getCurrentUser() {
        return super.getCurrentUser();
    }

    /**
     * Maneja las solicitudes GET al endpoint /usuarios/test-encode para realizar
     * una prueba de codificación de una cadena utilizando el servicio de usuario
     * (UsuarioService). Recibe una cadena como parámetro de consulta, y devuelve la
     * cadena codificada resultante después de aplicar el codificador de
     * contraseñas. Este endpoint se puede utilizar para verificar que el proceso de
     * codificación funciona correctamente y para ver cómo se codifica una cadena
     * específica.
     * 
     * @param cadena La cadena que se desea codificar utilizando el servicio de
     *               usuario. Esta cadena se pasará como parámetro de consulta en la
     *               solicitud GET.
     * @return La cadena codificada resultante después de aplicar el codificador de
     *         contraseñas. La salida será diferente cada vez que se codifique la
     *         misma cadena debido a la naturaleza del proceso de codificación. Este
     *         endpoint es principalmente para fines de prueba y demostración.
     */
    @GetMapping("/test-encode")
    public String testEncode(@RequestParam("cadena") String cadena) {
        return usuarioService.encodeString(cadena);
    }
}
