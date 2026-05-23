package com.places.api.domain.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO para representar la información del menú, incluyendo sus submenús (children) y su menú padre (padre).
 */
@Getter
@Setter
public class MenuDto {
    
    /**
     * ID del menú. Es un campo opcional, ya que al crear un nuevo menú no se proporcionará un ID. Sin embargo, al obtener o actualizar un menú existente, este campo será necesario para identificarlo de manera única.
     */
    private Integer menuId;

    /**
     * Nombre del menú. Es un campo obligatorio que representa el nombre del menú. Debe tener un valor no nulo y no vacío, con una longitud máxima de 50 caracteres.
     */
    private String nombre;

    /**
     * Ruta del menú. Es un campo opcional que representa la ruta o URL asociada al menú. Puede ser nulo o vacío, pero si se proporciona, debe tener una longitud máxima de 255 caracteres.
     */
    private String ruta;

    /**
     * Icono del menú. Es un campo opcional que representa el icono asociado al menú. Puede ser nulo o vacío, pero si se proporciona, debe tener una longitud máxima de 50 caracteres.
     */
    private String icono;

    /**
     * ID del menú padre. Es un campo opcional que representa el ID del menú padre al que pertenece este menú. Puede ser nulo si el menú no tiene un padre (es decir, es un menú raíz). Si se proporciona, debe ser un número entero positivo que corresponda al ID de un menú existente en la base de datos.
     */
    private Integer padreMenuId;

    /**
     * Menú padre. Es un campo opcional que representa el menú padre al que pertenece este menú. Puede ser nulo si el menú no tiene un padre (es decir, es un menú raíz). Si se proporciona, debe ser un objeto MenuDto que corresponda a un menú existente en la base de datos.
     */
    private MenuDto padre;

    /**
     * Submenús. Es un campo opcional que representa los submenús asociados a este menú. Puede ser nulo o vacío, pero si se proporciona, debe ser una lista de objetos MenuDto que correspondan a menús existentes en la base de datos.
     */
    private List<MenuDto> hijos;


    /**
     * ID del módulo al que pertenece el menú. Es un campo opcional que representa el ID del módulo al que pertenece este menú. Puede ser nulo si el menú no está asociado a ningún módulo. Si se proporciona, debe ser un número entero positivo que corresponda al ID de un módulo existente en la base de datos.
     */
    private Short moduloId;
}
