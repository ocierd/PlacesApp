package com.example.demo.services.interfaces;

/**
 * Interfaz que define los métodos para el envío de SMS.
 */
public interface SmsService {

    /**
     * Envía un SMS al número de teléfono especificado con el mensaje proporcionado.
     * 
     * @param destinoConCodigo El número de teléfono al que se enviará el SMS. El
     *                         número debe incluir el código de país (ejemplo: +52
     *                         para México).
     * @param mensaje          El mensaje que se enviará en el SMS
     */
    void enviarSMS(String destinoConCodigo, String mensaje);

}
