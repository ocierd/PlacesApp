package com.example.demo.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Telefono;
import com.example.demo.domain.Usuario;
import com.example.demo.domain.VerificacionTelefono;
import com.example.demo.domain.exceptions.NoEncontradoException;
import com.example.demo.domain.exceptions.ValidacionException;
import com.example.demo.repository.TelefonoRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.repository.VerificacionTelefonoRepository;
import com.example.demo.services.interfaces.SmsService;
import com.example.demo.services.interfaces.VerificacionTelefonoService;

/**
 * Implementación de la lógica de negocio para la verificación de teléfono. Se
 * encarga de generar códigos de verificación, enviarlos por SMS y validar los
 * códigos ingresados por el usuario.
 */
@Service
public class VerificacionTelefonoServiceImpl implements VerificacionTelefonoService {

    private static final Logger logger = LoggerFactory.getLogger(VerificacionTelefonoServiceImpl.class);

    @Value("${places_app.sms.expiration}")
    private Integer EXPIRATION_TIME;

    @Value("${places_app.sms.max_attempts}")
    private Integer MAX_ATTEMPTS;

    private final VerificacionTelefonoRepository verificacionTelefonoRepository;
    private final SmsService smsService;
    private final TelefonoRepository telefonoRepository;

    public VerificacionTelefonoServiceImpl(
            VerificacionTelefonoRepository verificacionTelefonoRepository,
            UsuarioRepository usuarioRepository,
            TelefonoRepository telefonoRepository,
            SmsService smsService) {
        this.verificacionTelefonoRepository = verificacionTelefonoRepository;
        // this.usuarioRepository = usuarioRepository;
        this.smsService = smsService;
        this.telefonoRepository = telefonoRepository;

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void enviarCodigoVerificacion(Usuario usuario, Long telefonoId) throws ValidacionException {
        try {
            // 1) Validar si el usuario ha rebasado la cantidad de tokens que se le pueden
            // enviar
            validarVerificacionesPrevias(usuario, telefonoId); // Descomentar para activar la validación de intentos

            // 2) Generar un nuevo código de verificación y guardarlo en la base de datos
            VerificacionTelefono verificacion = obtenerVerificacionTelefono(telefonoId);

            logger.info("Código de verificación generado para el usuario {}: {}", usuario.getUsuarioId(),
                    verificacion.getCodigo());

            // 3) Construir el mensaje de texto con el código de verificación
            String mensaje = obtenerMensaje(verificacion.getCodigo());

            // 4) Obtener el número de teléfono del usuario
            String telefono = obtenerNumeroTelefono(telefonoId);
            // String telefono = "+525537355085"; // Reemplazar con el número de teléfono
            // del usuario
            logger.info("Número de teléfono al que se enviará el código de verificación: {}", telefono);

            // 5) Enviar el mensaje de texto al número de teléfono del usuario
            enviarCodigoSms(telefono, mensaje);
        } catch (Exception e) {
            logger.error("Error al enviar el código de verificación", e);
            throw e;
        }
    }

    private void enviarCodigoSms(String telefono, String mensaje) {
        smsService.enviarSMS(telefono, mensaje);
    }

    private void validarVerificacionesPrevias(Usuario usuario, Long telefonoId) throws ValidacionException {
        Integer cantidadCodigosEnviados = verificacionTelefonoRepository
                .countByTelefonoId(telefonoId);

        // Revisa si ha rebasado la mayoría de intentos
        if (cantidadCodigosEnviados >= MAX_ATTEMPTS) {
            throw new ValidacionException(
                    "Se ha alcanzado el límite de códigos de verificación enviados. Por favor, inténtalo más tarde.");
        }
    }

    private String obtenerMensaje(String codigo) {
        return "Tu código de verificación es: " + codigo;
    }

    private VerificacionTelefono obtenerVerificacionTelefono(Long telefonoId) {
        VerificacionTelefono verTelefono = new VerificacionTelefono();
        String codigo = generarCodigo();
        verTelefono.setCodigo(codigo);
        LocalDateTime now = LocalDateTime.now();
        verTelefono.setFechaEnvio(now);
        verTelefono.setFechaExpiracion(now.plusSeconds(EXPIRATION_TIME));
        verTelefono.setTelefonoId(telefonoId);
        verificacionTelefonoRepository.save(verTelefono);
        return verTelefono;
    }

    private String generarCodigo() {
        return String.valueOf(ThreadLocalRandom.current().nextLong(100000, 999999));
    }

    private String obtenerNumeroTelefono(Long telefonoId) {

        Telefono tel = telefonoRepository.findByTelefonoId(telefonoId);
        String telefono = "+" + tel.getPais().getCodigo() + tel.getNumero();
        return telefono;

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void verificarTelefono(String codigo, Long telefonoId)
            throws ValidacionException, NoEncontradoException {
        /* Obtiene la lista de los codig */

        VerificacionTelefono verificacion = verificacionTelefonoRepository.findBytelefonoIdAndCodigo(telefonoId,
                codigo);

        if (verificacion != null) {
            if (verificacion.getFechaConfirmacion() != null) {
                throw new ValidacionException("El codigo ya fue validado");
            }

            if (verificacion.getFechaExpiracion().isBefore(LocalDateTime.now())) {
                throw new ValidacionException("El codigo ya expiro");
            }

            verificacion.setFechaConfirmacion(LocalDateTime.now());
            verificacionTelefonoRepository.save(verificacion);

            Telefono telefono = telefonoRepository.findByTelefonoId(telefonoId);

            if (telefono == null) {
                throw new ValidacionException("El telèfono no existe");
            }

            List<Telefono> telefonos = telefonoRepository.findByUsuarioId(telefono.getUsuarioId());
            if (!telefonos.isEmpty()) {

                for (Telefono tel : telefonos) {
                    tel.setActivo(false);
                }
           
            telefonoRepository.saveAll(telefonos);
             }
            telefono.setActivo(true);
            telefonoRepository.save(telefono);
        } else {
            throw new NoEncontradoException();
        }
    }

}
