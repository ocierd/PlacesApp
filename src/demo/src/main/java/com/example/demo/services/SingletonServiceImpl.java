package com.example.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase que implementa el patrón Singleton para proporcionar una única
 * instancia de un servicio en toda la aplicación. Este patrón es útil para
 * servicios que requieren una única instancia compartida.
 */
public class SingletonServiceImpl {

    private Logger logger = LoggerFactory.getLogger(SingletonServiceImpl.class);

    /**
     * Instancia única de SingletonServiceImpl. Se inicializa de forma perezosa
     */
    private static SingletonServiceImpl instance;

    private SingletonServiceImpl() {
        // Private constructor to prevent instantiation
    }

    /**
     * Obtiene la instancia única de SingletonServiceImpl. Si la instancia no existe,
     * se crea una nueva. Si ya existe, se devuelve la instancia existente.
     * @return La instancia única de SingletonServiceImpl
     */
    public static SingletonServiceImpl getInstance() {
        if (instance == null) {
            instance = new SingletonServiceImpl();
        }
        return instance;
    }

    public void someMethod() {
        // Implementation of the method
        logger.info("Ejecutando un método que requiere una instancia");
    }

    public void anotherMethod() {
        // Another method implementation
        logger.info("Ejecutando otro método que requiere una instancia");
    }

}
