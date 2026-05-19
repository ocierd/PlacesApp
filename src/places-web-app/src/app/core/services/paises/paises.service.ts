import { Injectable } from '@angular/core';
import { PlacesBaseService } from '@services/places-base-service';
import { Pais } from '@shared/models/pais.model';
import { Observable } from 'rxjs';

/**
 * Servicio para gestionar las operaciones relacionadas con los países, como obtener la lista de países disponibles.
 */
@Injectable({
  providedIn: 'root',
})
export class PaisesService extends PlacesBaseService {

  constructor() {
    super('paises');
  }


  /**
   *   Método para obtener la lista de países disponibles. Realiza una solicitud HTTP GET al endpoint "paises" de la API utilizando el método genérico `get` proporcionado por la clase base `PlacesBaseService`.
   * @returns Observable con la lista de países obtenida del endpoint "paises" de la API. El método utiliza el método genérico `get` proporcionado por la clase base `PlacesBaseService` para realizar la solicitud HTTP GET al endpoint correspondiente.
   */
  getPaises(): Observable<Pais[]> {
    return this.get<Pais[]>('');
  }

  /**
   * Método para buscar países por nombre. Realiza una solicitud HTTP GET al endpoint "buscar" de la API, agregando el parámetro de consulta "nombre" con el valor del criterio de búsqueda proporcionado.
   * @param criterio Criterio de búsqueda para filtrar los países por nombre. Se utiliza para construir la URL de la solicitud HTTP GET al endpoint "buscar" de la API, agregando el parámetro de consulta "nombre" con el valor del criterio proporcionado.
   * @returns Observable con la lista de países que coinciden con el criterio de búsqueda. El método utiliza el método genérico `get` proporcionado por la clase base `PlacesBaseService` para realizar la solicitud HTTP GET al endpoint correspondiente, pasando la URL construida con el criterio de búsqueda.
   */
  buscarPaises(criterio: string): Observable<Pais[]> {
    const url = `buscar?nombre=${criterio}`;
    return this.get<Pais[]>(url);
  }

}
