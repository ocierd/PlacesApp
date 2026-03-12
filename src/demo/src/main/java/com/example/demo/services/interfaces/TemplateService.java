package com.example.demo.services.interfaces;

import java.util.Map;

/**
 * Interfaz para el servicio de plantillas.
 * Proporciona métodos para renderizar plantillas con datos dinámicos.
 */
public interface TemplateService {

    /**
     * Renderiza una plantilla con los datos proporcionados.
     * 
     * @param templateName Nombre de la plantilla a renderizar.
     * @param data         Datos dinámicos para la plantilla.
     * @return Contenido renderizado de la plantilla.
     */
    String renderTemplate(String templateName, Map<String, Object> data);

}
