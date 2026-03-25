package com.example.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.client.RestTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureRestTestClient
public class PaisesControllerTests {

    @Autowired
    private RestTestClient restTestClient;

    @Test
    public void testGetPaises() {
        var response = restTestClient.get()
        .uri("/paises").exchange();
        response
                .expectStatus()
                .isOk();
        List<?> paises = response.expectBody(List.class).returnResult().getResponseBody();

        // Verificar que la lista de países no esté vacía
        assertNotEquals(0, paises.size());
        assertEquals(193, paises.size());

    }

}
