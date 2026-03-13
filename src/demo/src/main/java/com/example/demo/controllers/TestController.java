package com.example.demo.controllers;

import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Sms;
import com.example.demo.domain.TipoPago;
import com.example.demo.domain.dto.TestDto;
import com.example.demo.domain.exceptions.ValidacionException;
import com.example.demo.services.interfaces.SmsService;
import com.example.demo.services.interfaces.TestService;
import com.example.demo.services.interfaces.UsuarioService;

@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

    private final TestService testService;
    private final SmsService smsService;

    /**
     * Test
     * 
     * @param testService
     */
    public TestController(TestService testService, SmsService smsService) {
        this.testService = testService;
        this.smsService = smsService;
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

    @GetMapping("/sms")
    public void enviarSMS(@RequestParam String numero) {

        this.smsService.enviarSMS(numero, "Hola");
    }

    @PostMapping("/envio-codigo")

    public void procesarEnvioTokenSMS() throws ValidacionException {
        Long usuarioId = super.getCurrentUser().getUsuarioid();
        smsService.procesarEnvioTokenSMS(null);
    }

    @GetMapping("/generate")
    public String getString() {
        return RandomStringUtils.randomAlphanumeric(15);
    }

}
