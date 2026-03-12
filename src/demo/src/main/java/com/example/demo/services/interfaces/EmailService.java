package com.example.demo.services.interfaces;

import java.util.Map;

import com.example.demo.domain.entities.MailData;
import com.example.demo.domain.exceptions.ValidacionException;

public interface EmailService {

    /**
     * Envía un correo electrónico con los parámetros especificados.
     * 
     * @param to      Destinatario del correo electrónico.
     * @param subject Asunto del correo electrónico.
     * @param body    Cuerpo del correo electrónico.
     * @throws ValidacionException Si ocurre un error de validación.
     */
    void sendEmail(String to, String subject, String body) throws ValidacionException;

    /**
     * Envía un correo electrónico utilizando un objeto MailData que contiene todos
     * los detalles del correo.
     * 
     * @param mailData Objeto que contiene los detalles del correo electrónico.
     * @throws ValidacionException Si ocurre un error de validación.
     */
    void sendEmail(MailData mailData) throws ValidacionException;

    /**
     * Envía un correo electrónico utilizando una plantilla. Este método valida que
     * se haya proporcionado una plantilla y los datos necesarios para renderizarla,
     * luego utiliza el TemplateService para generar el contenido del correo
     * electrónico a partir de la plantilla y los datos, y finalmente llama al
     * método sendEmail para enviar el correo electrónico generado.
     * 
     * @param mailData Objeto que contiene los detalles del correo electrónico,
     *                 incluyendo la plantilla y los datos para renderizarla.
     * @throws ValidacionException Si ocurre un error de validación, como falta de
     *                             plantilla o datos.
     */
    void sendEmailFromTemplate(MailData mailData) throws ValidacionException;

    /**
     * Envía un correo electrónico utilizando una plantilla y los datos
     * proporcionados.
     * 
     * @param to           Destinatario del correo electrónico.
     * @param subject      Asunto del correo electrónico.
     * @param template     Nombre de la plantilla a utilizar.
     * @param templateData Datos para renderizar la plantilla.
     * @throws ValidacionException Si ocurre un error de validación.
     */
    void sendEmailFromTemplate(String to, String subject, String template, Map<String, Object> templateData)
            throws ValidacionException;
}
