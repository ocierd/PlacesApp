package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.VerificacionCorreo;

@Repository
public interface VerificacionCorreoRepository extends JpaRepository<VerificacionCorreo, Long> {

        /**
         * Busca una verificación de correo por su token único.
         * 
         * @param token El token único asociado a la verificación de correo
         * @return La verificación de correo que coincide con el token, o null si no se
         *         encuentra ninguna coincidencia
         */
        VerificacionCorreo findByToken(UUID token);

        /**
         * Cuenta la cantidad de verificaciones de correo asociadas a un correo
         * específico que han sido confirmadas (es decir, que tienen una fecha de
         * confirmación no nula).
         * 
         * @param correoId El ID del correo al que están asociadas las verificaciones de
         *                 correo
         * @return La cantidad de verificaciones de correo asociadas al correo que han
         *         sido confirmadas
         */
        Integer countByCorreoIdAndFechaConfirmacionIsNotNull(Long correoId);

        /**
         * Cuenta la cantidad de verificaciones de correo asociadas a un correo
         * específico que no han sido confirmadas (es decir, que tienen una fecha de
         * confirmación nula).
         * 
         * @param correoId El ID del correo al que están asociadas las verificaciones de
         *                 correo
         * @return La cantidad de verificaciones de correo asociadas al correo que no
         *         han sido confirmadas
         */
        Integer countByCorreoIdAndFechaConfirmacionIsNull(Long correoId);

        /**
         * Cuenta la cantidad de verificaciones de correo asociadas a un correo
         * específico que no han expirado (es decir, que tienen una fecha de expiración
         * posterior a la fecha actual).
         * 
         * @param correoId El ID del correo al que están asociadas las verificaciones de
         *                 correo
         * @param fecha    La fecha actual que se utiliza para comparar con la fecha de
         *                 expiración de las verificaciones de correo
         * @return La cantidad de verificaciones de correo asociadas al correo que no
         *         han expirado
         */
        Integer countByCorreoIdAndFechaExpiracionAfter(Long correoId, LocalDateTime fecha);
}