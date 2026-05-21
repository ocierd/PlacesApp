import { Injectable } from '@angular/core';
import { PlacesBaseService } from '@services/places-base-service';
import { Usuario, UsuarioRegistroDto } from '@shared/models/usuario.model';

import { Observable } from 'rxjs';

/**
 * Servicio para gestionar las operaciones relacionadas con los países, como obtener la lista de países disponibles.
 */
@Injectable({
  providedIn: 'root',
})
export class UsuarioService extends PlacesBaseService {

  constructor() {
    super('usuarios');
  }


  crearUsuario(datos: UsuarioRegistroDto): Observable<Usuario> {
    return this.post<Usuario>('',datos);

  }


  

}
