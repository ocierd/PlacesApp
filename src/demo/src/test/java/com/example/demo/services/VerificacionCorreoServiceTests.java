package com.example.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import com.example.demo.domain.Correo;
import com.example.demo.domain.Usuario;
import com.example.demo.domain.VerificacionCorreo;
import com.example.demo.domain.exceptions.NoEncontradoException;
import com.example.demo.domain.exceptions.ValidacionException;
import com.example.demo.repository.CorreoRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.VerificacionCorreoRepository;
import com.example.demo.services.interfaces.VerificacionEmailService;

/**
 * VerificacionCorreoServiceTests es una clase de prueba que contiene pruebas
 * unitarias para la implementación de la lógica de negocio para la verificación
 * de correo electrónico. Utiliza Spring Boot Test para cargar el contexto de la
 * aplicación y Mockito para simular el comportamiento de los repositorios
 * durante las pruebas.
 */
@SpringBootTest
public class VerificacionCorreoServiceTests {

        /**
         * Simulación del VerificacionCorreoRepository para controlar el comportamiento
         * de las consultas a la base de datos relacionadas con las verificaciones de
         * correo durante las pruebas.
         */
        @MockitoBean
        private VerificacionCorreoRepository verificacionCorreoRepository;

        /**
         * Simulación del CorreoRepository para controlar el comportamiento de las
         * consultas a la base de datos relacionadas con los correos durante las
         * pruebas.
         */
        @MockitoBean
        private CorreoRepository correoRepository;

        @MockitoBean
        private UsuarioRepository usuarioRepository;

        /**
         * Inyección del VerificacionEmailService para realizar pruebas directas a sus
         * métodos.
         */
        @Autowired
        private VerificacionEmailService verificacionEmailService;

        /**
         * Prueba para verificar que el método enviarCorreoVerificacionToken envía un
         * correo de verificación con un token único al usuario. Esta prueba simula el
         * comportamiento de los repositorios para encontrar el correo activo del
         * usuario, contar las verificaciones de correo asociadas al correo y guardar la
         * nueva verificación de correo en la base de datos. Además, se verifica que el
         * método no lance ninguna excepción durante la ejecución.
         * 
         * @throws ValidacionException Si ocurre un error de validación durante la
         *                             creación del token o el envío del correo
         *                             electrónico
         */
        @Test
        void enviarCorreoVerificacionToken_Ok() throws ValidacionException {
                Long correoId = 1L;
                Long usuarioId = 1L;
                Correo correo = new Correo();
                correo.setCorreoId(correoId);
                correo.setActivo(null);
                correo.setUsuarioId(usuarioId);
                correo.setCorreoElectronico("brypapanu@gmail.com");

                // Simular el comportamiento del repositorio para encontrar el correo activo del
                // usuario
                when(correoRepository.findByUsuarioIdAndActivoIsNull(usuarioId))
                                .thenReturn(Optional.of(correo));

                // Simular el comportamiento del repositorio para contar las verificaciones de
                // correo asociadas al correo
                when(verificacionCorreoRepository.countByCorreoIdAndFechaConfirmacionIsNotNull(correoId))
                                .thenReturn(0);

                // Simular el comportamiento del repositorio para contar las verificaciones de
                // correo no confirmadas asociadas al correo
                when(verificacionCorreoRepository.countByCorreoIdAndFechaConfirmacionIsNull(correoId))
                                .thenReturn(0);

                // Simular el comportamiento del repositorio para contar las verificaciones de
                // correo no expiradas asociadas al correo
                when(verificacionCorreoRepository.countByCorreoIdAndFechaExpiracionAfter(correoId, LocalDateTime.now()))
                                .thenReturn(0);

                VerificacionCorreo verificacionCorreo = new VerificacionCorreo();
                verificacionCorreo.setVerificacionCorreoId(1L);
                verificacionCorreo.setCorreoId(correoId);
                verificacionCorreo.setToken(UUID.randomUUID());

                when(verificacionCorreoRepository.save(any()))
                                .thenReturn(verificacionCorreo);

                Usuario usuario = new Usuario();
                usuario.setUsuarioId(usuarioId);
                usuario.setNombre("Brigitte");
                usuario.setApellidoPaterno("Papanú");
                usuario.setApellidoMaterno("Ferrer");

                verificacionEmailService.enviarCorreoVerificacionToken(usuario);
        }

        /**
         * Prueba para verificar que el método enviarCorreoVerificacionToken lanza una
         * ValidacionException cuando el usuario no tiene un correo activo para activar.
         * 
         */
        @Test
        void enviarCorreoVerificacionToken_NoTieneCorreoValidacionException() {

                Long usuarioId = 1L;

                // Simular el comportamiento del repositorio para encontrar el correo del
                // usuario.
                // Regresando un Optional vacío para simular que el usuario no tiene un correo
                // para activar
                when(correoRepository.findByUsuarioIdAndActivoIsNull(usuarioId))
                                .thenReturn(Optional.empty());

                Usuario usuario = new Usuario();
                usuario.setUsuarioId(usuarioId);
                usuario.setNombre("Fernando");
                usuario.setApellidoPaterno("Ricardo");
                usuario.setApellidoMaterno("Moran");

                ValidacionException ex = assertThrows(ValidacionException.class, () -> {
                        verificacionEmailService.enviarCorreoVerificacionToken(usuario);
                });

                assertEquals("No tiene correo para activar.", ex.getMessage());

        }

        @Test
        void enviarCorreoVerificacionToken_CorreoYaValidadoValidacionException() {

                Long correoId = 1L;
                Long usuarioId = 1L;
                Correo correo = new Correo();
                correo.setCorreoId(correoId);
                correo.setActivo(null);
                correo.setUsuarioId(usuarioId);
                correo.setCorreoElectronico("brypapanu@gmail.com");

                Usuario usuario = new Usuario();
                usuario.setUsuarioId(usuarioId);
                usuario.setNombre("Brigitte");
                usuario.setApellidoPaterno("Papanú");
                usuario.setApellidoMaterno("Ferrer");

                // Simular el comportamiento del repositorio para encontrar el correo activo del
                // usuario
                when(correoRepository.findByUsuarioIdAndActivoIsNull(usuarioId))
                                .thenReturn(Optional.of(correo));

                // Simular el comportamiento del repositorio para contar las verificaciones de
                // correo asociadas al correo
                when(verificacionCorreoRepository.countByCorreoIdAndFechaConfirmacionIsNotNull(correoId))
                                .thenReturn(1);

                ValidacionException ex = assertThrows(ValidacionException.class, () -> {
                        verificacionEmailService.enviarCorreoVerificacionToken(usuario);
                });

                assertEquals("El correo ya fue verificado.", ex.getMessage());
        }

        @Test
        void enviarCorreoVerificacionToken_VeficacionesPreviasRebasadasError() {

                Long correoId = 1L;
                Long usuarioId = 1L;
                Correo correo = new Correo();
                correo.setCorreoId(correoId);
                correo.setActivo(null);
                correo.setUsuarioId(usuarioId);
                correo.setCorreoElectronico("brypapanu@gmail.com");

                Usuario usuario = new Usuario();
                usuario.setUsuarioId(usuarioId);
                usuario.setNombre("Brigitte");
                usuario.setApellidoPaterno("Papanú");
                usuario.setApellidoMaterno("Ferrer");

                // Simular el comportamiento del repositorio para encontrar el correo activo del
                // usuario
                when(correoRepository.findByUsuarioIdAndActivoIsNull(usuarioId))
                                .thenReturn(Optional.of(correo));

                // Simular el comportamiento del repositorio para contar las verificaciones de
                // correo asociadas al correo
                when(verificacionCorreoRepository.countByCorreoIdAndFechaConfirmacionIsNotNull(correoId))
                                .thenReturn(0);

                // Simular el comportamiento del repositorio para contar las verificaciones de
                // correo no confirmadas asociadas al correo
                when(verificacionCorreoRepository.countByCorreoIdAndFechaConfirmacionIsNull(correoId))
                                .thenReturn(4);

                ValidacionException ex = assertThrows(ValidacionException.class, () -> {
                        verificacionEmailService.enviarCorreoVerificacionToken(usuario);
                });

                assertEquals("Se ha alcanzado el límite de códigos de verificación enviados. Por favor, inténtalo más tarde.",
                                ex.getMessage());
        }

        @Test
        void enviarCorreoVerificacionToken_VeficacionNoVencidaError() {

                Long correoId = 1L;
                Long usuarioId = 1L;
                Correo correo = new Correo();
                correo.setCorreoId(correoId);
                correo.setActivo(null);
                correo.setUsuarioId(usuarioId);
                correo.setCorreoElectronico("brypapanu@gmail.com");

                Usuario usuario = new Usuario();
                usuario.setUsuarioId(usuarioId);
                usuario.setNombre("Brigitte");
                usuario.setApellidoPaterno("Papanú");
                usuario.setApellidoMaterno("Ferrer");

                // Simular el comportamiento del repositorio para encontrar el correo activo del
                // usuario
                when(correoRepository.findByUsuarioIdAndActivoIsNull(usuarioId))
                                .thenReturn(Optional.of(correo));

                // Simular el comportamiento del repositorio para contar las verificaciones de
                // correo asociadas al correo
                when(verificacionCorreoRepository.countByCorreoIdAndFechaConfirmacionIsNotNull(correoId))
                                .thenReturn(0);

                // Simular el comportamiento del repositorio para contar las verificaciones de
                // correo no confirmadas asociadas al correo
                when(verificacionCorreoRepository.countByCorreoIdAndFechaConfirmacionIsNull(correoId))
                                .thenReturn(2);

                // Simular el comportamiento del repositorio para contar las verificaciones de
                // correo no expiradas asociadas al correo
                when(verificacionCorreoRepository.countByCorreoIdAndFechaExpiracionAfter(eq(correoId), any(LocalDateTime.class)))
                                .thenReturn(5);

                ValidacionException ex = assertThrows(ValidacionException.class, () -> {
                        verificacionEmailService.enviarCorreoVerificacionToken(usuario);
                });

                assertEquals("Tiene un Token no vencido.", ex.getMessage());
        }

        @Test
        void enviarCorreoVerificacionToken_SaveCorreoVerificacion() throws ValidacionException {
                Long correoId = 1L;
                Long usuarioId = 1L;
                Correo correo = new Correo();
                correo.setCorreoId(correoId);
                correo.setActivo(null);
                correo.setUsuarioId(usuarioId);
                correo.setCorreoElectronico("");

                // Simular el comportamiento del repositorio para encontrar el correo activo del
                // usuario
                when(correoRepository.findByUsuarioIdAndActivoIsNull(usuarioId))
                                .thenReturn(Optional.of(correo));

                // Simular el comportamiento del repositorio para contar las verificaciones de
                // correo asociadas al correo
                when(verificacionCorreoRepository.countByCorreoIdAndFechaConfirmacionIsNotNull(correoId))
                                .thenReturn(0);

                // Simular el comportamiento del repositorio para contar las verificaciones de
                // correo no confirmadas asociadas al correo
                when(verificacionCorreoRepository.countByCorreoIdAndFechaConfirmacionIsNull(correoId))
                                .thenReturn(0);

                // Simular el comportamiento del repositorio para contar las verificaciones de
                // correo no expiradas asociadas al correo
                when(verificacionCorreoRepository.countByCorreoIdAndFechaExpiracionAfter(correoId, LocalDateTime.now()))
                                .thenReturn(0);

                VerificacionCorreo verificacionCorreo = new VerificacionCorreo();
                verificacionCorreo.setVerificacionCorreoId(1L);
                verificacionCorreo.setCorreoId(correoId);

                when(verificacionCorreoRepository.save(any()))
                                .thenReturn(null);

                Usuario usuario = new Usuario();
                usuario.setUsuarioId(usuarioId);
                usuario.setNombre("Brigitte");
                usuario.setApellidoPaterno("Papanú");
                usuario.setApellidoMaterno("Ferrer");

                ValidacionException ex = assertThrows(ValidacionException.class, () -> {
                        verificacionEmailService.enviarCorreoVerificacionToken(usuario);
                });

                assertEquals("Error al enviar el correo de verificación de token para el correo {}", correo.getCorreoElectronico(), ex.getMessage());
        }

        @Test
        void verificarCorreo_Ok() throws NoEncontradoException, ValidacionException {
                UUID uuid = UUID.randomUUID();
                String token = uuid.toString();

                Long correoId = 1L;
                Long correo2Id = 2L;
                Long usuarioId = 1L;

                LocalDateTime ahora = LocalDateTime.now();
                LocalDateTime masUnaHora = ahora.plusHours(1);

                Correo correo = new Correo();
                correo.setCorreoId(correoId);
                correo.setActivo(null);
                correo.setUsuarioId(usuarioId);
                correo.setCorreoElectronico("brypapanu@gmail.com");

                VerificacionCorreo verificacionCorreo = new VerificacionCorreo();
                verificacionCorreo.setVerificacionCorreoId(1L);
                verificacionCorreo.setCorreoId(correoId);
                verificacionCorreo.setToken(uuid);
                verificacionCorreo.setFechaExpiracion(masUnaHora);
                verificacionCorreo.setCorreo(correo);

                when(verificacionCorreoRepository.findByToken(uuid)).thenReturn(verificacionCorreo);

                //when(verificacionCorreo.getFechaExpiracion().isBefore(LocalDateTime.now())).thenReturn(false);

                //when(verificacionCorreo.getFechaConfirmacion()).thenReturn(null);

                //when(verificacionCorreo.getCorreo()).thenReturn(correo);

                Usuario usuario = new Usuario();
                usuario.setUsuarioId(usuarioId);
                usuario.setNombre("Brigitte");
                usuario.setApellidoPaterno("Papanú");
                usuario.setApellidoMaterno("Ferrer");

                when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));

                Correo correoActivo = new Correo();
                correoActivo.setCorreoId(correo2Id);
                correoActivo.setActivo(true);
                correoActivo.setUsuarioId(usuarioId);
                correoActivo.setCorreoElectronico("admin@brypapanu.com.mx");
                List<Correo> correosActivos = new ArrayList<>();
                correosActivos.add(correoActivo);

                when(correoRepository.findByUsuarioIdAndActivoTrue(usuario.getUsuarioId())).thenReturn(correosActivos);

                verificacionEmailService.verificarCorreo(token);
        }

        @Test
        void verificarCorreo_SinTokenError() {
                String token = "";

                Exception ex = assertThrows(Exception.class, () -> {
                        verificacionEmailService.verificarCorreo(token);
                });
        }

        @Test
        void verificarCorreo_TokenNoEncontrado() {
                UUID uuid = UUID.randomUUID();
                String token = uuid.toString();

                when(verificacionCorreoRepository.findByToken(uuid)).thenReturn(null);

                ValidacionException ex = assertThrows(ValidacionException.class, () -> {
                        verificacionEmailService.verificarCorreo(token);
                });

                assertEquals("Token inválido.", ex.getMessage());
        }

        @Test
        void verificarCorreo_UsuarioNoEncontrado() {
                UUID uuid = UUID.randomUUID();
                String token = uuid.toString();

                Long correoId = 1L;
                Long usuarioId = 1L;

                LocalDateTime ahora = LocalDateTime.now();
                LocalDateTime masUnaHora = ahora.plusHours(1);

                Correo correo = new Correo();
                correo.setCorreoId(correoId);
                correo.setActivo(null);
                correo.setUsuarioId(usuarioId);
                correo.setCorreoElectronico("brypapanu@gmail.com");

                VerificacionCorreo verificacionCorreo = new VerificacionCorreo();
                verificacionCorreo.setVerificacionCorreoId(1L);
                verificacionCorreo.setCorreoId(correoId);
                verificacionCorreo.setToken(uuid);
                verificacionCorreo.setFechaExpiracion(masUnaHora);
                verificacionCorreo.setCorreo(correo);

                when(verificacionCorreoRepository.findByToken(uuid)).thenReturn(verificacionCorreo);

                when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.empty());

                ValidacionException ex = assertThrows(ValidacionException.class, () -> {
                        verificacionEmailService.verificarCorreo(token);
                });

                assertEquals("Usuario inválido.", ex.getMessage());
        }
}