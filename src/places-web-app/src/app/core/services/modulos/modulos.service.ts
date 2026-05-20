import { Injectable, Signal, signal, WritableSignal } from '@angular/core';
import { PlacesBaseService } from '@services/places-base-service';
import { ModuloDto } from '@shared/models/modulo.model';
import { StorageService } from '@shared/services/storage/storage.service';
import { Observable } from 'rxjs';


/**
 * Servicio para gestionar los módulos disponibles para el usuario, proporcionando métodos para obtener los módulos, seleccionar un módulo y mantener el estado del módulo seleccionado. Utiliza señales reactivas para gestionar el estado del módulo seleccionado y se inyecta a nivel raíz para estar disponible en toda la aplicación.
 */
@Injectable({
  providedIn: 'root',
})
export class ModulosService extends PlacesBaseService {

  /**
   * Señal reactiva que mantiene el estado del módulo seleccionado. Permite a los componentes suscribirse a cambios en el módulo seleccionado y reaccionar en consecuencia. Inicialmente, el valor es null, lo que indica que no se ha seleccionado ningún módulo.
   */
  private static selected: WritableSignal<ModuloDto | null> = signal(null);

  /**
   * Clave utilizada para almacenar el módulo seleccionado en el almacenamiento local. Esto permite persistir la selección del módulo incluso después de que el usuario cierre la aplicación o recargue la página.
   */
  private readonly selectedModuloKey = 'selectedModulo';


  constructor(private storageService: StorageService) {
    super("modulos");
  }


  /**
   * Obtiene la lista de módulos disponibles para el usuario autenticado. Este método realiza una solicitud HTTP al endpoint '/usuario' para obtener los módulos asociados al usuario. Los componentes pueden suscribirse a este observable para recibir la lista de módulos y mostrarla en la interfaz de usuario, por ejemplo, en un diálogo de selección de módulos durante el proceso de inicio de sesión.
   * @returns Un observable que emite una lista de módulos disponibles para el usuario. Este método realiza una solicitud HTTP al endpoint '/usuario' para obtener los módulos asociados al usuario autenticado. Los componentes pueden suscribirse a este observable para recibir la lista de módulos y mostrarla en la interfaz de usuario, por ejemplo, en un diálogo de selección de módulos durante el proceso de inicio de sesión.
   */
  getModulosPorUsuario(): Observable<ModuloDto[]> {
    return this.get<ModuloDto[]>('/usuario');
  }

  /**
   * Establece el módulo seleccionado por el usuario. Este método actualiza la señal 'selected' con el módulo seleccionado y también almacena esta selección en el almacenamiento local utilizando la clave definida en 'selectedModuloKey'. Esto permite que la selección del módulo persista incluso después de que el usuario cierre la aplicación o recargue la página. Los componentes que estén suscritos a la señal 'selected' podrán reaccionar a este cambio y actualizar su estado en consecuencia.
   * @param modulo Módulo a seleccionar. Este método actualiza la señal 'selected' con el módulo seleccionado y también almacena esta selección en el almacenamiento local utilizando la clave definida en 'selectedModuloKey'. Esto permite que la selección del módulo persista incluso después de que el usuario cierre la aplicación o recargue la página. Los componentes que estén suscritos a la señal 'selected' podrán reaccionar a este cambio y actualizar su estado en consecuencia.
   */
  setSelectedModulo(modulo: ModuloDto | null): void {
    this.storageService.setObject(this.selectedModuloKey, modulo);
    ModulosService.selected.set(modulo);
  }

  /**
   * Obtiene el módulo seleccionado actualmente. Este método devuelve una señal de solo lectura que emite el módulo seleccionado. Los componentes pueden suscribirse a esta señal para recibir actualizaciones en tiempo real cuando el módulo seleccionado cambie. Esto permite que la interfaz de usuario reaccione automáticamente a los cambios en la selección del módulo, como actualizar la navegación o mostrar información relevante para el módulo seleccionado.
   * @returns Una señal de solo lectura que emite el módulo seleccionado actualmente. Los componentes pueden suscribirse a esta señal para recibir actualizaciones en tiempo real cuando el módulo seleccionado cambie. Esto permite que la interfaz de usuario reaccione automáticamente a los cambios en la selección del módulo, como actualizar la navegación o mostrar información relevante para el módulo seleccionado.
   */
  getSelectedModulo(): Signal<ModuloDto | null> {
    return ModulosService.selected.asReadonly();
  }

}
