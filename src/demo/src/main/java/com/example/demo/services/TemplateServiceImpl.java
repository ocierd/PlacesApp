package com.example.demo.services;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.demo.services.interfaces.TemplateService;

/**
 * Proporciona métodos para renderizar plantillas con datos dinámicos.
 */
@Service
public class TemplateServiceImpl implements TemplateService {

    private final TemplateEngine templateEngine;

    public TemplateServiceImpl(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    /**
     * Renderiza una plantilla con los datos proporcionados.
     * 
     * @param templateName Nombre de la plantilla a renderizar.
     * @param data         Datos dinámicos para la plantilla.
     * @return Contenido renderizado de la plantilla.
     */
    @Override
    public String renderTemplate(String templateName, Map<String, Object> data) {
        Context context = new Context();
        context.setVariables(data);
        String resultadoPlantilla = templateEngine.process(templateName, context);
        return resultadoPlantilla;
    }

}
