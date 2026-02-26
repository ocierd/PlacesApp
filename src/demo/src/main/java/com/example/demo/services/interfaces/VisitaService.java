package com.example.demo.services.interfaces;

import com.example.demo.domain.Visita;

public interface VisitaService {

    /**
     * Crea una nueva visita en la base de datos. Recibe un objeto Visita con los
     * datos de la visita a crear y devuelve la visita creada.
     * 
     * @param visita El objeto Visita que se desea crear
     * @return La visita creada
     */
    Visita crearVisita(Visita visita);

    /**
     * Actualiza el estado de una visita a "visitado". Recibe el ID de la visita a
     * actualizar y devuelve la visita actualizada con el nuevo estado.
     * 
     * @param visitaId El ID de la visita que se desea actualizar
     * @return La visita actualizada con el nuevo estado "visitado"
     */
    Visita actualizarVisitado(Long visitaId);
}
