import { Injectable } from '@angular/core';
import { PlacesBaseService } from '../places-base-service';
import { LoginData, TokenData } from '@shared/models/login.model';
import { Observable } from 'rxjs';
import { StorageService } from '@shared/services/storage/storage.service';

/**
 * Servicio para autenticación de usuarios. Extiende de PlacesBaseService y proporciona métodos específicos para autenticación, como el método `auth` que realiza una solicitud POST al endpoint de login.
 * Este servicio se inyecta a nivel raíz, lo que significa que estará disponible en toda la aplicación.
 */
@Injectable({
  providedIn: 'root',
})
export class AuthService extends PlacesBaseService {
  
  private readonly storageKey = 'authToken';

  constructor(private storage:StorageService) {
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

  isLoggedIn(): boolean {
    const token = this.getToken();
    if (!token) {
      return false;
    }
    return true;
    // const now = Date.now();
    // const expiresAt = token.expiresIn * 1000 + now; // Convertir a milisegundos
    // return now < expiresAt;
  }


  /**
   * Almacena el token de autenticación en el almacenamiento local utilizando el servicio StorageService.
   * @param token Datos del token a almacenar en el almacenamiento local.
   */
  setToken(token: TokenData): void {
    this.storage.setObject(this.storageKey, token);
  }

  /**
   * Obtiene el token de autenticación almacenado en el almacenamiento local utilizando el servicio StorageService.
   * @returns Datos del token almacenado o null si no existe.
   */
  getToken(): TokenData | null {
    return this.storage.getAs<TokenData>(this.storageKey);
  }

  /**
   * Elimina el token de autenticación almacenado en el almacenamiento local utilizando el servicio StorageService.
   */
  clearToken(): void {
    this.storage.removeItem(this.storageKey);
  }

}
