package com.example.demo.controllers;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;

import com.example.demo.domain.dto.LoginDataDto;

/**
 * TestControllerTests es una clase de prueba que contiene pruebas unitarias
 * para el TestController. Utiliza Spring Boot Test para cargar el contexto de
 * la aplicación y RestTestClient para realizar solicitudes HTTP a los endpoints
 * del TestController. Las pruebas verifican el correcto funcionamiento de los
 * métodos del TestController, incluyendo la respuesta de los endpoints y la
 * validación de los datos devueltos por los métodos. Además, se incluye una
 * prueba para el envío de correos electrónicos, que verifica tanto el caso
 * exitoso como los casos de validación de correos electrónicos inválidos o
 * vacíos. Para la prueba de envío de correos electrónicos, se obtiene un token
 * JWT válido utilizando las credenciales de prueba definidas en el archivo
 * application.yml de pruebas, y se utiliza este token para autenticar las
 * solicitudes a los endpoints protegidos del TestController. Las pruebas de
 * validación de correos electrónicos verifican que se devuelvan los mensajes de
 * error adecuados cuando se proporcionan correos electrónicos inválidos o
 * vacíos, y que se devuelva una respuesta exitosa cuando se proporciona un
 * correo electrónico válido.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
public class TestControllerTests {

    /**
     * Inyección del TestController para realizar pruebas directas a sus métodos.
     */
    @Autowired
    private TestController testController;

    /**
     * Inyección del RestTestClient para realizar solicitudes HTTP a los endpoints
     * del TestController durante las pruebas de integración.
     */
    @Autowired
    private RestTestClient restTestClient;

    private static String jwt;

    /**
     * Prueba para el método hello() del TestController. Verifica que la respuesta
     * sea "Hello, World!".
     */
    @Test
    public void testHello() {
        String response = testController.hello();
        assert (response.equals("Hello, World!"));
    }

    /**
     * Prueba para el método getGreeting() del TestController. Verifica que la respuesta
     * sea "Hello from TestServiceImpl!".
     */

    @Test
    public void testGetGreeting() {
        String response = testController.getGreeting();
        assert (response.equals("Hello from TestServiceImpl!"));
    }

    /**
     * Prueba para el método testDto() del TestController. Verifica que el DTO
     * devuelto tenga el ID 1L y el mensaje "DTO Test Message".
     */
    @Test
    public void testTestDto() {
        var dto = testController.testDto();
        assert (dto.getId() == 1L);
        assert (dto.getMessage().equals("DTO Test Message"));
    }

    /**
     * Prueba para el método sendEmailTest() del TestController. Verifica que se
     * envíe un correo electrónico de prueba correctamente utilizando un correo
     * electrónico válido como destinatario. Esta prueba también verifica que se
     * obtenga un token JWT válido para autenticar la solicitud al endpoint protegido
     * del TestController.
     */
    @Test
    public void sendEmailTest_Ok() {
        String destinatario = "ocierd@msn.com";
        String token = getToken();
        String url = "/test/email?to=" + destinatario;
        restTestClient.get()
                .uri(url)
                .header("Authorization", "Bearer " + token)
                .exchange()
                .expectStatus()
                .isOk();

    }

    /**
     * Prueba para el método sendEmailTest() del TestController. Verifica que se
     * devuelva una respuesta de error adecuada cuando se proporciona un correo
     * electrónico inválido como destinatario. Esta prueba también verifica que se
     * obtenga un token JWT válido para autenticar la solicitud al endpoint protegido
     * del TestController.
     */
    @Test
    void testSendEmailTest_InvalidEmail() {
        String destinatario = "invalid-email";
        String token = getToken();
        String url = "/test/email?to=" + destinatario;
        var response = restTestClient.get()
                .uri(url)
                .header("Authorization", "Bearer " + token)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.SC_CONFLICT);

        response.expectBody()
                .jsonPath("$.error")
                .isEqualTo("El correo electrónico debe contener '@'");
    }

    /**
     * Prueba para el método sendEmailTest() del TestController. Verifica que se
     * devuelva una respuesta de error adecuada cuando se proporciona un correo
     * electrónico vacío como destinatario. Esta prueba también verifica que se
     * obtenga un token JWT válido para autenticar la solicitud al endpoint protegido
     * del TestController.
     */
    @Test
    void testSendEmailTest_EmptyEmail() {
        String destinatario = "";
        String token = getToken();
        String url = "/test/email?to=" + destinatario;
        var response = restTestClient.get()
                .uri(url)
                .header("Authorization", "Bearer " + token)
                .exchange()
                .expectStatus()
                .isEqualTo(HttpStatus.SC_CONFLICT);

        response.expectBody()
                .jsonPath("$.error")
                .isEqualTo("El correo electrónico no puede estar vacío");
    }

    /**
     * Método auxiliar para obtener un token JWT válido utilizando las credenciales de
     * prueba definidas en el archivo application.yml de pruebas. Este método realiza
     * una solicitud al endpoint de autenticación para obtener el token JWT, y lo
     * almacena en una variable estática para reutilizarlo en las pruebas posteriores.
     *
     * @return Un token JWT válido para autenticar las solicitudes a los endpoints protegidos del TestController.
     */
    private String getToken() {

        if (jwt == null) {

            LoginDataDto loginData = new LoginDataDto(
                    "fer", "password123");
            restTestClient.post()
                    .uri("/auth/login")
                    .body(loginData)
                    .exchange()
                    .expectStatus().isOk()
                    .expectBody()
                    .jsonPath("$.token")
                    .value(token -> {
                        jwt = token.toString();
                    });

        }
        return jwt;
    }

}
