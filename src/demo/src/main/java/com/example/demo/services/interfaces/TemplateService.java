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


    /**
     * Renderiza una plantilla con un solo dato clave-valor. Este método es una conveniencia para casos donde solo se necesita pasar un dato a la plantilla, evitando la necesidad de crear un mapa completo.
     * @param templateName Nombre de la plantilla a renderizar.
     * @param key Clave del dato a pasar a la plantilla.
     * @param value Valor del dato a pasar a la plantilla.
     * @return Contenido renderizado de la plantilla.
     */
    String renderTemplate(String templateName,String key, Object value);

}
