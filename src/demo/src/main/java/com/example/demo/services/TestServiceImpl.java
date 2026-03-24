package com.example.demo.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.example.demo.domain.builder.CasaBuilder;
import com.example.demo.domain.dto.Knight;
import com.example.demo.domain.dto.TestDto;
import com.example.demo.domain.entities.Casa;
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

    private final Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

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

    /**
     * Construye una lista de casas de prueba utilizando el patrón Builder. Este
     * método se encarga de crear varias instancias de la clase Casa con diferentes
     * configuraciones y atributos, utilizando el CasaBuilder para facilitar la
     * construcción de los objetos. La lista de casas construida se puede utilizar
     * para pruebas, demostraciones o como datos de ejemplo en la aplicación.
     */
    @Override
    public List<Casa> buildCasasTest() {

        List<Casa> casas = new ArrayList<>();

        Casa casa = new CasaBuilder()
                .buildNumeroHabitaciones(2)
                .buildNumeroBanios(1)
                .buildNumeroPuertasExteriores(1)
                .build();
        logger.info("Casa sencilla creada: {}", casa);
        casas.add(casa);

        Casa casaCompleja = new CasaBuilder()
                .buildNumeroPisos(2)
                .buildNumeroHabitaciones(4)
                .buildNumeroBanios(3)
                .buildNumeroGarajes(2)
                .buildNumeroPuertasExteriores(2)
                .buildAreaConstruida(250.0)
                .buildAreaTotal(500.0)
                .buildTieneJardin(true)
                .buildTienePiscina(true)
                .buildTipoCasa("Casa de lujo")
                .build();

        logger.info("Casa compleja creada: {}", casaCompleja);
        casas.add(casaCompleja);

        Casa casaConCocinas = new CasaBuilder()
                .buildNumeroPisos(2)
                .buildNumeroHabitaciones(4)
                .buildNumeroBanios(3)
                .buildNumeroGarajes(2)
                .buildNumeroPuertasExteriores(2)
                .buildAreaConstruida(250.0)
                .buildAreaTotal(500.0)
                .buildTieneJardin(true)
                .buildTienePiscina(true)
                .buildTipoCasa("Casa de lujo")
                .buildNumeroCocinas((short) 2)
                .build();

        logger.info("Casa con cocinas creada: {}", casaConCocinas);
        casas.add(casaConCocinas);

        return casas;
    }

    /**
     * Prueba del patrón Singleton. Este método se encarga de obtener dos instancias
     * del servicio SingletonServiceImpl utilizando su método getInstance() y luego
     * ejecutar algunos métodos en ambas instancias para demostrar que se trata de
     * la misma instancia compartida. Además, se registran mensajes de log para
     * mostrar el comportamiento del patrón Singleton en acción.
     */
    @Override
    public void singletonTest() {
        SingletonServiceImpl singleton1 = SingletonServiceImpl.getInstance();
        SingletonServiceImpl singleton2 = SingletonServiceImpl.getInstance();
        singleton1.someMethod();

        singleton2.anotherMethod();

        logger.info("Ejecutando singletonTest en TestServiceImpl");

    }

}
