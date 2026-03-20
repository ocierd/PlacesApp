package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.VerificacionTelefono;

/**
 * Repositorio para la entidad VerificacionTelefono.
 */
@Repository
public interface VerificacionTelefonoRepository extends JpaRepository<VerificacionTelefono, Long> {

        /**
         * Busca una verificación de teléfono por el ID del usuario asociado.
         * 
         * @param usuarioId El ID del usuario al que está asociada la verificación de
         *                  teléfono
         * @return Una Optional que contiene la verificación de teléfono si se
         *         encuentra, o una Optional vacía si no se encuentra
         */
         VerificacionTelefono findBytelefonoIdAndCodigo(Long telefonoId,String codigo);

        /**
         * Cuenta la cantidad de verificaciones de teléfono asociadas a un usuario. Este
         * método se utiliza para validar si el usuario ha rebasado la cantidad de
         * tokens que se le pueden enviar.
         * 
         * @param usuarioId El ID del usuario al que están asociadas las verificaciones
         *                  de teléfono
         * @return La cantidad de verificaciones de teléfono asociadas al usuario
         */
        Integer countByTelefonoId(Long telefonoId);



}
