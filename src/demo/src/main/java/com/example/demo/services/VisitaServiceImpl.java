package com.example.demo.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Visita;
import com.example.demo.repository.VisitaRepository;
import com.example.demo.services.interfaces.VisitaService;

/**
 * VisitaServiceImpl es una clase que implementa la interfaz VisitaService y
 * proporciona la lógica de negocio para manejar las visitas en la aplicación.
 */
@Service
public class VisitaServiceImpl implements VisitaService {

    /**
     * Logger para registrar eventos y errores en la clase VisitaServiceImpl.
     * Utiliza SLF4J con Logback como implementación de logging.
     */

    private static final Logger logger = LoggerFactory.getLogger(VisitaServiceImpl.class);

    private final VisitaRepository visitaRepository;

    public VisitaServiceImpl(VisitaRepository visitaRepository) {
        this.visitaRepository = visitaRepository;
    }

    @Override
    public Visita crearVisita(Visita visita) {
        Visita creada = visitaRepository.save(visita);
        Long vistaId = creada.getVisitaId();
        if (vistaId != null) {
            return visitaRepository.findById(vistaId)
                    .orElse(null);

        }
        return creada;
    }

    /**
     * Actualiza el estado de una visita a "visitado". Recibe el ID de la visita a
     * actualizar y devuelve la visita actualizada con el nuevo estado.
     * 
     * @param visitaId El ID de la visita que se desea actualizar
     * @return La visita actualizada con el nuevo estado "visitado"
     */
    @Override
    @Transactional
    public Visita actualizarVisitado(Long visitaId) {
        try {

            Optional<Visita> visitaOpt = visitaRepository.findById(visitaId);
            if (visitaOpt.isPresent()) {
                Visita visita = visitaOpt.get();
                visita.setVisitado(true);
                visitaRepository.save(visita);
                return visita;
            }
            return null;
        } catch (Exception e) {
            logger.error("Error al actualizar visitado: {}", e.getMessage(), e);
            throw e;
        }

    }

}
