package com.example.demo.domain.entities;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase que representa los datos de un correo electrónico.
 * Contiene información sobre los destinatarios, asunto, cuerpo, plantilla y datos adicionales para la plantilla.
 */
@Getter
@Setter
public class MailData {

    /**
     * Lista de destinatarios (To).
     */
    private List<String> destinatariosList;

    /**
     * Lista de destinatarios en copia (CC).
     */
    private List<String> ccList;


    /**
     * Lista de destinatarios en copia oculta (BCC).
     */
    private List<String> bccList;

    
    /**
     * Asunto del correo electrónico.
     */
    private String asunto;

    
    /**
     * Cuerpo del correo
     */
    private String body;


    /**
     * Plantilla del correo
     */
    private String template;


    /**
     * Indica si el cuerpo del correo es HTML o texto plano
     */
    private boolean isHtml;


    /**
     * Datos adicionales para la plantilla del correo, si se utiliza una plantilla. Esto permite pasar variables dinámicas a la plantilla para generar el contenido del correo de manera flexible.
     */
    private Map<String,Object> templateData;
    


    public Map<String, Object> getTemplateData() {
        return templateData;
    }

    public void setTemplateData(Map<String, Object> templateData) {
        this.templateData = templateData;
    }

    public boolean isHtml() {
        return isHtml;
    }

    public void setHtml(boolean isHtml) {
        this.isHtml = isHtml;
    }

    public List<String> getDestinatariosList() {
        return destinatariosList;
    }

    public void setDestinatariosList(List<String> destinatariosList) {
        this.destinatariosList = destinatariosList;
    }

    public List<String> getCcList() {
        return ccList;
    }

    public void setCcList(List<String> ccList) {
        this.ccList = ccList;
    }

    public List<String> getBccList() {
        return bccList;
    }

    public void setBccList(List<String> bccList) {
        this.bccList = bccList;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public MailData() {
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
