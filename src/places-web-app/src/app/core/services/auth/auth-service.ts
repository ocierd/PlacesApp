import { Injectable } from '@angular/core';
import { PlacesBaseService } from '../places-base-service';
import { LoginData, TokenData } from '@shared/models/login.model';
import { Observable } from 'rxjs';

/**
 * Servicio para autenticación de usuarios. Extiende de PlacesBaseService y proporciona métodos específicos para autenticación, como el método `auth` que realiza una solicitud POST al endpoint de login.
 * Este servicio se inyecta a nivel raíz, lo que significa que estará disponible en toda la aplicación.
 */
@Injectable({
  providedIn: 'root',
})
export class AuthService extends PlacesBaseService {

  constructor() {
    super('auth');
  }


  /**
   * Método para autenticar a un usuario.
   * @param data Datos de inicio de sesión.
   * @returns Observable con los datos del token.
   */
  auth(data: LoginData): Observable<TokenData> {
    return this.post<TokenData>('login', data);
  }

}
