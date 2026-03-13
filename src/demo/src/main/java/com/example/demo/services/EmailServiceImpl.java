package com.example.demo.services;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.demo.domain.entities.MailData;
import com.example.demo.domain.exceptions.ValidacionException;
import com.example.demo.services.interfaces.EmailService;
import com.example.demo.services.interfaces.TemplateService;

import jakarta.mail.MessagingException;
import jakarta.mail.Message.RecipientType;
import jakarta.mail.internet.MimeMessage;

/**
 * Implementación del servicio de correo electrónico
 */
@Service
public class EmailServiceImpl implements EmailService {

    /**
     * Logger para el seguimiento de eventos y errores en el servicio de correo
     * electrónico
     */
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    /**
     * Constante para el tipo de contenido HTML en el correo electrónico
     */
    private static final String HTML_CONTENT_TYPE = "text/html; charset=utf-8";

    /**
     * Inyección de dependencia del JavaMailSender, que es el componente principal
     * para enviar correos electrónicos en Spring. Este componente se configura
     * automáticamente a través de las propiedades de Spring Boot relacionadas con
     * el correo electrónico (como host, puerto, usuario, contraseña, etc.) y se
     * utiliza para crear y enviar mensajes de correo electrónico.
     */
    private final JavaMailSender mailSender;

    /**
     * Inyección de dependencia del TemplateService, que es un servicio
     * personalizado para renderizar plantillas de correo electrónico. Este servicio
     * se utiliza para generar el contenido del correo electrónico a partir de
     * plantillas predefinidas y datos dinámicos, lo que permite crear correos
     * electrónicos personalizados y atractivos sin tener que construir manualmente
     * el HTML en el código.
     */
    private final TemplateService templateService;

    @Value("${spring.mail.username}")
    private String CORREO_PRINCIPAL;

    /**
     * Constructor para la inyección de dependencias del JavaMailSender y el
     * TemplateService. Estos componentes son esenciales para el funcionamiento del
     * servicio de correo electrónico, ya que el JavaMailSender se encarga de enviar
     * los correos electrónicos, mientras que el TemplateService se encarga de
     * renderizar las plantillas de correo electrónico con los datos proporcionados.
     * Al utilizar la inyección de dependencias, se facilita la gestión de estas
     * dependencias y se promueve un diseño más modular y fácil de probar para el
     * servicio de correo electrónico.
     * 
     * @param mailSender      Inyección de dependencia del JavaMailSender para
     *                        enviar correos electrónicos.
     * @param templateService Inyección de dependencia del TemplateService para
     *                        renderizar plantillas de correo electrónico.
     */
    public EmailServiceImpl(JavaMailSender mailSender, TemplateService templateService) {
        this.mailSender = mailSender;
        this.templateService = templateService;
    }

    /**
     * Envía un correo electrónico con los parámetros especificados.
     * 
     * @param to      Destinatario del correo electrónico.
     * @param subject Asunto del correo electrónico.
     * @param body    Cuerpo del correo electrónico.
     * @throws ValidacionException Si ocurre un error de validación.
     */
    @Override
    public void sendEmail(String to, String subject, String body) throws ValidacionException {
        MailData mailData = new MailData();
        mailData.setDestinatariosList(List.of(to));
        mailData.setAsunto(subject);
        mailData.setBody(body);
        mailData.setHtml(false); // Asumimos que el cuerpo es texto plano por defecto
        sendEmail(mailData);

    }

    /**
     * Envía un correo electrónico utilizando un objeto MailData que contiene todos
     * los detalles del correo.
     * 
     * @param mailData Objeto que contiene los detalles del correo electrónico.
     * @throws ValidacionException Si ocurre un error de validación.
     */
    @Override
    public void sendEmail(MailData mailData) throws ValidacionException {

        try {
            MimeMessage message = mailSender.createMimeMessage(); // new MimeMessage(createEmailSession());
            message.setFrom(CORREO_PRINCIPAL);

            if (mailData.getDestinatariosList() == null || mailData.getDestinatariosList().isEmpty()) {
                throw new ValidacionException("La lista de destinatarios no puede estar vacía");
            }

            // Agregar destinatarios (To)
            for (String to : mailData.getDestinatariosList()) {
                message.addRecipients(RecipientType.TO, to);
            }

            // Agregar destinatarios (CC)
            if (mailData.getCcList() != null) {
                for (String cc : mailData.getCcList()) {
                    message.addRecipients(RecipientType.CC, cc);
                }
            }

            // Agregar destinatarios (BCC)
            if (mailData.getBccList() != null) {
                for (String bcc : mailData.getBccList()) {
                    message.addRecipients(RecipientType.BCC, bcc);
                }
            }

            // Establece el asunto
            message.setSubject(mailData.getAsunto());

            // Establece el cuerpo del correo, diferenciando entre HTML y texto plano
            if (mailData.isHtml()) {
                message.setContent(mailData.getBody(), HTML_CONTENT_TYPE);
            } else {
                message.setText(mailData.getBody());
            }
            mailSender.send(message);

            // Loguear el destinatario principal para seguimiento
            String destinatioPrincipal = mailData.getDestinatariosList().get(0);
            logger.info("Correo enviado a " + destinatioPrincipal);
        } catch (MailException e) {
            logger.error("Error al enviar correo (MailException)", e);
        } catch (MessagingException ex) {
            logger.error("Error al enviar correo (MessagingException):", ex);
        }
    }

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
    @Override
    public void sendEmailFromTemplate(MailData mailData) throws ValidacionException {

        if (mailData.getTemplate() == null || mailData.getTemplate().isEmpty()) {
            throw new ValidacionException("El nombre de la plantilla no puede estar vacío");
        }

        if (mailData.getTemplateData() == null || mailData.getTemplateData().isEmpty()) {
            throw new ValidacionException("Los datos para la plantilla no pueden estar vacíos");
        }

        String content = templateService.renderTemplate(mailData.getTemplate(), mailData.getTemplateData());
        mailData.setBody(content);
        mailData.setHtml(true); // Asumimos que las plantillas generan contenido HTML
        sendEmail(mailData);

    }

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
    @Override
    public void sendEmailFromTemplate(String to, String subject, String template, Map<String, Object> templateData)
            throws ValidacionException {
        MailData mailData = new MailData();
        mailData.setDestinatariosList(List.of(to));
        mailData.setAsunto(subject);
        mailData.setTemplate(template);
        mailData.setTemplateData(templateData);
        mailData.setHtml(true); // Asumimos que las plantillas generan contenido HTML
        sendEmailFromTemplate(mailData);
    }

}
