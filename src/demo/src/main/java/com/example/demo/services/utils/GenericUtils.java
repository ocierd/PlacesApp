package com.example.demo.services.utils;

public class GenericUtils {
    


    /**
     * Verifica si un valor dado está entre dos valores de inicio y fin. Esto es útil para determinar si un valor se traslapa con otro valor existente.
     * @param <T> El tipo de los valores a comparar, que debe implementar la interfaz Comparable
     * @param value Valor que se desea verificar si está entre el valor de inicio y el valor de fin
     * @param inicio Valor de inicio del rango
     * @param fin Valor de fin del rango
     * @return true si el valor está entre el inicio y el fin, false en caso contrario
     */
    public static <T extends Comparable<T>> boolean isBetween(T value, T inicio, T fin) {
        if (inicio.compareTo(fin) >= 0) {
            throw new IllegalArgumentException("El valor de inicio debe ser menor que el valor de fin");
        }
        return inicio.compareTo(value) <= 0 && fin.compareTo(value) >= 0;
    }

}
