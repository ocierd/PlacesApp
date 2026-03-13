package com.example.demo.services.interfaces;

import com.example.demo.domain.exceptions.ValidacionException;

/**
 * Interfaz
 */
public interface TestService {
    String getGreeting();

    /**
     * Envía un correo electrónico de prueba utilizando una plantilla Thymeleaf
     * 
     * @param correo Destinatario del correo electrónico
     * @throws ValidacionException Si ocurre un error de validación al enviar el
     *                             correo electrónico
     */
    void sendEmailTest(String correo) throws ValidacionException;
}
