package com.example.demo.services;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.services.interfaces.SmsService;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

/**
 * Implementación del servicio de envío de SMS utilizando Twilio.
 */
@Service
public class SmsServiceImpl implements SmsService {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(SmsServiceImpl.class);

    @Value("${twilio.config.from_number}")
    private String FROM_NUMBER;

    /**
     * Envía un SMS al número de teléfono especificado con el mensaje proporcionado.
     * 
     * @param destinoConCodigo El número de teléfono al que se enviará el SMS. El
     *                         número debe incluir el código de país (ejemplo: +52
     *                         para México).
     * @param mensaje          El mensaje que se enviará en el SMS
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enviarSMS(String destinoConCodigo, String mensaje) {

        try {
            PhoneNumber to = new PhoneNumber(destinoConCodigo);
            PhoneNumber from = new PhoneNumber(FROM_NUMBER);

            MessageCreator creator = Message.creator(
                    to, // Destino
                    from, // Origen
                    mensaje) // Mensaje
            ;
            Message message = creator.create();
            String body = message.getBody();

            logger.info("SID del mensaje: {}", message.getSid());
            logger.info("Cuerpo del mensaje: {}", body);
        } catch (ApiException e) {
            logger.error("Error al enviar SMS: {}", e.getMessage());
            throw e; // Re-lanzar la excepción para que el controlador pueda manejarla
        }

    }

}
