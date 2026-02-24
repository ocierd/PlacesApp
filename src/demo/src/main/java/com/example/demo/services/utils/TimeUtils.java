package com.example.demo.services.utils;

import java.time.LocalTime;

public class TimeUtils {

    /**
     * Verifica si un tiempo dado está entre dos tiempos de inicio y fin. Esto es
     * útil para determinar si un horario se traslapa con otro horario existente.
     * 
     * @param time   El tiempo que se desea verificar si está entre el tiempo de
     *               inicio y fin
     * @param inicio El tiempo de inicio del rango
     * @param fin    El tiempo de fin del rango
     * @return true si el tiempo está entre el inicio y el fin, false en caso
     *         contrario
     */
    public static boolean isBetween(LocalTime time, LocalTime inicio, LocalTime fin) {
        // [9:00, 18:00] -> 9:00 <= time <= 18:00

        // inicio < time => -1
        // inicio == time => 0
        // inicio > time => 1

        // boolean result = inicio.compareTo(time) <= 0 && fin.compareTo(time) >= 0;
        // return result;

        return GenericUtils.isBetween(time, inicio, fin);

    }

}
