package com.example.demo.domain.dto;

/**
 * Clase TestDto que representa un objeto de transferencia de datos para pruebas
 */
public class TestDto {

    /**
     * Identificador único del TestDto
     */
    private Long id;

    /**
     * Mensaje del TestDto
     */
    private String message;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
