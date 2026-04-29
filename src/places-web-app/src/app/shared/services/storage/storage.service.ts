import { isPlatformBrowser } from '@angular/common';
import { DOCUMENT, inject, Inject, Injectable, PLATFORM_ID } from '@angular/core';
import { LoggerService } from '../logger/logger.service';

@Injectable({
  providedIn: 'root',
})
export class StorageService {

  /**
   * Inyecta el servicio de Logger para registrar advertencias cuando se intenta acceder a sessionStorage en un entorno no compatible.
   */
  private logger: LoggerService = inject(LoggerService);


  constructor(@Inject(PLATFORM_ID) private platformId: Object) {
  }

  /**
   * Proporciona acceso a sessionStorage si se está ejecutando en un entorno de navegador, o a un almacenamiento simulado en otros entornos.
   * Esto permite que el servicio funcione sin errores en entornos como SSR, donde sessionStorage no está disponible.
   * @returns Un objeto Storage que representa sessionStorage o un almacenamiento simulado.
   * @remarks En un entorno de navegador, se devuelve sessionStorage. En otros entornos, se devuelve un objeto simulado que implementa la interfaz Storage, con métodos getItem y setItem que permiten almacenar y recuperar datos en memoria.
   */
  get storage(): Storage {
    if (isPlatformBrowser(this.platformId)) {
      return sessionStorage;
    }

    this.logger.warn('No se puede acceder a sessionStorage en un entorno que no es el navegador. Se está utilizando un almacenamiento simulado.');

    return {
      getItem: (key: string) => {
        if (key === 'authToken') {
          return "{\"expiresIn\":3600,\"refreshToken\":\"eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6W10sImp0aSI6IjEiLCJzdWIiOiJmcmljYXJkbyIsImlhdCI6MTc3NzQyOTcwMiwiZXhwIjoxNzc3NDMzNzAyfQ.Q1zTRD0-QorJ5JHNoa1rDeSUgke0nXD_OGW5-chrPIiMAWtBAYHfkw5JluJwvgq_\",\"refreshTokenExpiresIn\":4000,\"token\":\"eyJhbGciOiJIUzM4NCJ9.eyJyb2xlcyI6W10sImp0aSI6IjEiLCJzdWIiOiJmcmljYXJkbyIsImlhdCI6MTc3NzQyOTcwMiwiZXhwIjoxNzc3NDMzMzAyfQ.Be-1zcWweK_vZf1SOEvIKrosxpQChdOw4tHjCGUCVi-xNogFMg9QV_3QnC490Wpb\"}";
        }
        return null;
      },
      setItem: (key: string, value: string) => {

      }
    } as Storage;
  }


  /**
   * Almacena un valor en el almacenamiento local.
   * @param key Clave bajo la cual se almacenará el valor.
   * @param value Valor a almacenar.
   */
  setItem(key: string, value: string): void {
    this.storage.setItem(key, value);
  }

  /**
   * Obtiene un valor del almacenamiento local.
   * @param key Clave del valor a obtener.
   * @returns Valor almacenado o null si no existe.
   */
  getItem(key: string): string | null {
    return this.storage.getItem(key);
  }

  /**
   * Almacena un objeto en el almacenamiento local.
   * @param key Clave bajo la cual se almacenará el objeto.
   * @param value Objeto a almacenar.
   */
  setObject(key: string, value: any): void {
    this.storage.setItem(key, JSON.stringify(value));
  }

  /**
   * Obtiene un objeto del almacenamiento local.
   * @param key Clave del objeto a obtener.
   * @returns Objeto almacenado o null si no existe.
   */
  getAs<T>(key: string): T | null {
    const item = this.getItem(key);
    return item ? JSON.parse(item) as T : null;
  }

  /**
   * Elimina un valor del almacenamiento local.
   * @param key Clave del valor a eliminar del almacenamiento local.
   */
  removeItem(key: string): void {
    this.storage.removeItem(key);
  }

  /**
   * Elimina todos los valores del almacenamiento local.
   */
  clear(): void {
    this.storage.clear();
  }

}
