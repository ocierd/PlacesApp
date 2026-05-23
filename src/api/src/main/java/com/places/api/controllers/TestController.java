package com.places.api.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.places.api.domain.dto.TestDto;
import com.places.api.domain.entities.Casa;
import com.places.api.domain.exceptions.ValidacionException;
import com.places.api.services.interfaces.TestService;

@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

    private final TestService testService;

    /**
     * Test
     * 
     * @param testService
     * @param emailService
     */
    public TestController(TestService testService) {
        this.testService = testService;
    }

    /**
     * Primer API
     * 
     * @return Hola Mundo
     */
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }

    /**
     * API con interfaz
     * 
     * @return Saludo
     */
    @GetMapping("/greeting")
    public String getGreeting() {
        return this.testService.getGreeting();
    }

    @GetMapping("/dto")
    public TestDto testDto() {
        TestDto dto = new TestDto();
        dto.setId(1L);
        dto.setMessage("DTO Test Message");

        return dto;
    }

    /**
     * API para enviar email de prueba
     */
    @GetMapping("/email")
    public void sendEmailTest(@RequestParam("to") String destinatario)
            throws ValidacionException {
        testService.sendEmailTest(destinatario);
    }



    @GetMapping("/casas")
    public List<Casa> getCasasTest() {
        return testService.buildCasasTest();
    }

}
