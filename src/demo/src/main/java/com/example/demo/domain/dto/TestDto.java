package com.example.demo.domain.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Clase TestDto que representa un objeto de transferencia de datos para pruebas
 */
@Getter
@Setter
public class TestDto {

    /**
     * Identificador único del TestDto
     */
    private Long id;

    /**
     * Mensaje del TestDto
     */
    private String message;

    
    /**
     * Constructor por defecto para TestDto
     */
    public TestDto(){
    }


    /**
     * Constructor para TestDto
     */
    public TestDto(Long id, String message) {
        this.id = id;
        this.message = message;
    }

}
