package com.example.demo.services.interfaces;

import com.example.demo.domain.Usuario;

/**
 * Interfaz que define los métodos relacionados con la gestión de usuarios en la
 * aplicación, como la creación de usuarios y la codificación de contraseñas.
 */
public interface UsuarioService {

    /**
     * Crea un nuevo usuario en la aplicación. Este método toma un objeto Usuario,
     * codifica su contraseña y lo guarda en el repositorio de usuarios. Devuelve el
     * usuario creado con su ID asignado.
     * 
     * @param usuario El Usuario que contiene la información del usuario a crear,
     *                incluyendo su nombre de usuario y contraseña sin codificar.
     * @return El Usuario creado, con su contraseña codificada y su ID asignado por
     *         la base de datos. Si el proceso de creación falla, devuelve null.
     */
    Usuario crearUsuario(Usuario usuario);

    /**
     * Codifica una cadena utilizando el codificador de contraseñas configurado en
     * la aplicación. Este método se puede utilizar para codificar contraseñas u
     * otras cadenas que requieran codificación antes de ser almacenadas o
     * comparadas.
     * 
     * @param str La cadena que se desea codificar.
     * @return La cadena codificada resultante después de aplicar el codificador de
     *         contraseñas. La salida será diferente cada vez que se codifique la
     *         misma cadena debido a la naturaleza del proceso de codificación.
     */
    String encodeString(String str);

}
