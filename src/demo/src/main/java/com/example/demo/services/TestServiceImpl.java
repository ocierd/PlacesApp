package com.example.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.domain.dto.Knight;
import com.example.demo.domain.dto.TestDto;
import com.example.demo.domain.exceptions.ValidacionException;
import com.example.demo.services.interfaces.EmailService;
import com.example.demo.services.interfaces.TestService;

/**
 * Servicio
 */
@Service
public class TestServiceImpl implements TestService {

    private final String PLANTILLA_EMAIL_TEST = "email/test_template";

    private final String ASUNTO_TEST = "Correo de prueba";

    /**
     * Inyección de dependencia de Knight
     */
    private final Knight knight;

    private final EmailService emailService;

    /**
     * Constructor
     * 
     * @param knight Inyección de dependencia
     */
    public TestServiceImpl(Knight knight, EmailService emailService) {
        this.knight = knight;
        this.emailService = emailService;
    }

    /**
     * Se implementa la interfaz gatGreeting
     */
    @Override
    public String getGreeting() {
        knight.getWeapon().attack();
        return "Hello from TestServiceImpl!";
    }

    /**
     * Envía un correo electrónico de prueba utilizando una plantilla Thymeleaf
     * 
     * @param correo Destinatario del correo electrónico
     * @throws ValidacionException Si ocurre un error de validación al enviar el
     *                             correo electrónico
     */
    @Override
    public void sendEmailTest(String correo) throws ValidacionException {
        Map<String, Object> datosPlantilla = new HashMap<String, Object>();
        datosPlantilla.put("title", "Test Email");
        datosPlantilla.put("subtitle", "Este es un correo de prueba");

        List<TestDto> lista = new ArrayList<>();
        lista.add(new TestDto(1L, "Mensaje 1"));
        lista.add(new TestDto(2L, "Mensaje 2"));
        lista.add(new TestDto(3L, "Mensaje 3"));
        datosPlantilla.put("datos", lista);

        emailService.sendEmailFromTemplate(correo,
                ASUNTO_TEST, PLANTILLA_EMAIL_TEST, datosPlantilla);
    }

}
