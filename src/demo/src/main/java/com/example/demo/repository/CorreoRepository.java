package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Correo;

/**
 * Repositorio para la entidad Correo. Este repositorio proporciona métodos
 * para realizar operaciones CRUD y consultas específicas relacionadas con los
 * correos electrónicos de los usuarios. Incluye métodos para buscar correos
 * por dirección de correo electrónico, por ID de usuario y por estado de
 * activación.
 */
@Repository
public interface CorreoRepository extends JpaRepository<Correo, Long> {

    /**
     * Busca un correo electrónico activo por su dirección de correo electrónico.
     * 
     * @param correoElectronico La dirección de correo electrónico a buscar
     * @return Una Optional que contiene el correo electrónico encontrado si existe
     *         y está activo, o una Optional vacía si no se encuentra ningún correo
     *         electrónico activo con esa dirección
     */
    Optional<Correo> findByCorreoElectronicoAndActivoTrue(String correoElectronico);

    /**
     * Busca un correo electrónico por el ID del usuario asociado y su estado de
     * activación. Este método se utiliza para validar si el usuario tiene un correo
     * electrónico activo o inactivo.
     * 
     * @param UsuarioId El ID del usuario al que está asociado el correo electrónico
     * @return Una Optional que contiene el correo electrónico encontrado si existe
     *         y está inactivo, o una Optional vacía si no se encuentra ningún
     *         correo electrónico inactivo con ese ID de usuario
     */
    Optional<Correo> findByUsuarioIdAndActivoIsNull(Long UsuarioId);

    /**
     * Busca todos los correos electrónicos activos asociados a un usuario por su
     * ID.
     * 
     * @param UsuarioId El ID del usuario al que están asociados los correos
     *                  electrónicos
     * @return Una lista de correos electrónicos activos asociados al usuario, o una
     *         lista vacía si no se encuentran correos electrónicos activos con ese
     *         ID de usuario
     */
    List<Correo> findByUsuarioIdAndActivoTrue(Long UsuarioId);
}
