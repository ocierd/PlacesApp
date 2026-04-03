package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.example.demo.domain.Pais;
import com.example.demo.domain.Telefono;
import com.example.demo.domain.Usuario;
import com.example.demo.domain.exceptions.ValidacionException;
import com.example.demo.repository.TelefonoRepository;
import com.example.demo.repository.VerificacionTelefonoRepository;
import com.example.demo.services.interfaces.SmsService;
import com.example.demo.services.interfaces.VerificacionTelefonoService;

/**
 * VerificacionTelefonoServiceTests es una clase de prueba que contiene pruebas
 * unitarias para la implementación de la lógica de negocio para la verificación
 * de teléfono. Utiliza Spring Boot Test para cargar el contexto de la
 * aplicación y Mockito para simular el comportamiento de los repositorios
 * durante las pruebas.
 */
@SpringBootTest
public class VerificacionTelefonoServiceTests {

    /**
     * Inyección del VerificacionTelefonoService para realizar pruebas directas a
     * sus métodos.
     */
    @Autowired
    private VerificacionTelefonoService verificacionTelefonoService;

    /**
     * Simulación del VerificacionTelefonoRepository para controlar el
     * comportamiento de las consultas a la base de datos relacionadas con las
     * verificaciones de teléfono durante las pruebas.
     */

    @MockitoBean
    private VerificacionTelefonoRepository verificacionTelefonoRepository;

    /**
     * Simulación del TelefonoRepository para controlar el comportamiento de las
     * consultas a la base de datos relacionadas con los teléfonos durante las
     * pruebas.
     */
    @MockitoBean
    private TelefonoRepository telefonoRepository;

    /**
     * Simulación del SmsService para controlar el comportamiento del envío de SMS
     * durante las pruebas, evitando el envío real de mensajes de texto y
     * permitiendo la verificación de que el método de envío se llama correctamente
     * con los parámetros esperados.
     */
    @MockitoBean
    private SmsService smsService;


    // Configuración inicial antes de cada prueba, si es necesario
    @BeforeEach
    void setUp() {
        // Configurar el comportamiento simulado del SmsService para que no lance excepciones durante las pruebas
        doNothing().when(smsService)
                .enviarSMS(any(), any());
    }

    /**
     * Prueba para verificar que el método enviarCodigoVerificacion envía un código
     * de verificación al número de teléfono del usuario sin lanzar ninguna
     * excepción.
     * 
     * @throws ValidacionException Si ocurre un error de validación durante el envío
     *                             del código de verificación
     */
    @Test
    void enviarCodigoVerificacion_Ok() throws ValidacionException {

        Long usuarioId = 1L;
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(usuarioId);

        Long telefonoId = 1L;

        when(verificacionTelefonoRepository.countByTelefonoId(telefonoId))
                .thenReturn(2);

        Telefono telefono = new Telefono();
        telefono.setNumero("5541943983");
        telefono.setActivo(false);
        telefono.setUsuarioId(usuarioId);
        Pais pais = new Pais();
        pais.setCodigo("52");
        telefono.setPais(pais);
        Optional<Telefono> telefonoOptional = Optional.of(telefono);

        when(telefonoRepository.findById(telefonoId))
                .thenReturn(telefonoOptional);

        verificacionTelefonoService
                .enviarCodigoVerificacion(usuario, telefonoId);
    }

    /**
     * Prueba para verificar que el método enviarCodigoVerificacion lanza una
     * excepción cuando se ha alcanzado el límite de códigos de verificación
     * enviados.
     */
    @Test
    void enviarCodigoVerificacion_VeficacionesPreviasRebasadasError() {

        Long usuarioId = 1L;
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(usuarioId);

        Long telefonoId = 1L;

        when(verificacionTelefonoRepository.countByTelefonoId(telefonoId))
                .thenReturn(4);

        ValidacionException valor = assertThrows(ValidacionException.class, () -> {
            verificacionTelefonoService
                    .enviarCodigoVerificacion(usuario, telefonoId);
        });

        assertEquals("Se ha alcanzado el límite de códigos de verificación enviados. Por favor, inténtalo más tarde.",
                valor.getMessage());

    }

    /**
     * Prueba para verificar que el método enviarCodigoVerificacion lanza una
     * excepción cuando el teléfono no existe.
     */
    @Test
    void enviarCodigoVerificacion_TelefonoNoexisteError() {

        Long usuarioId = 1L;
        Usuario usuario = new Usuario();
        usuario.setUsuarioId(usuarioId);

        Long telefonoId = 1L;
        when(verificacionTelefonoRepository.countByTelefonoId(telefonoId))
                .thenReturn(2);

        Optional<Telefono> telefonoOptional = Optional.empty();
        when(telefonoRepository.findById(telefonoId))
                .thenReturn(telefonoOptional);

        ValidacionException valor = assertThrows(ValidacionException.class, () -> {
            verificacionTelefonoService
                    .enviarCodigoVerificacion(usuario, telefonoId);
        });

        assertEquals("El teléfono no existe", valor.getMessage());
    }

}
