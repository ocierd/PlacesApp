import { isPlatformBrowser } from '@angular/common';
import { DOCUMENT, Inject, Injectable, PLATFORM_ID } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class StorageService {

  // private storage: Storage;


  get storage(): Storage {
    if (isPlatformBrowser(this.platformId)) {
      return sessionStorage;
    }
    throw new Error("No existe documento para inyectar el sessionStorage");

  }

  constructor(@Inject(DOCUMENT) private document: Document, @Inject(PLATFORM_ID) private platformId: Object) {

    // if (this.document.defaultView?.sessionStorage) {
    //   this.storage = this.document.defaultView?.sessionStorage;
    // } else {
    //   throw new Error("No existe documento para inyectar el sessionStorage");
    // }


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
