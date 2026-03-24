package com.example.demo.services.interfaces;

import java.util.List;

import com.example.demo.domain.entities.Casa;
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

    /**
     * Construye una lista de casas de prueba utilizando el patrón Builder. Este
     * método se encarga de crear varias instancias de la clase Casa con diferentes
     * configuraciones y atributos, utilizando el CasaBuilder para facilitar la
     * construcción de los objetos. La lista de casas construida se puede utilizar
     * para pruebas, demostraciones o como datos de ejemplo en la aplicación.
     */
    List<Casa> buildCasasTest();


    void singletonTest();
}
