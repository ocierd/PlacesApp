import { Component, input, InputSignal, output, OutputEmitterRef, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'app-spinner-button',
  standalone: false,
  templateUrl: './spinner-button.component.html',
  styleUrl: './spinner-button.component.scss',
  encapsulation: ViewEncapsulation.None
})
export class SpinnerButtonComponent {


  /**
   * Texto a mostrar en el botón
   */
  texto: InputSignal<string> = input("");

  /**
   * Indica si se está realizando una acción que requiere mostrar el spinner. Cuando es true, se muestra el spinner y el botón se deshabilita para evitar múltiples clics.
   * El valor de esta propiedad se puede actualizar desde el componente padre para controlar la visualización del spinner y el estado del botón.
  */
  cargando: InputSignal<boolean> = input(false);

  /**
   * Indica si el botón está deshabilitado. Cuando es true, el botón no se puede interactuar.
   * El valor de esta propiedad se puede actualizar desde el componente padre para controlar el estado del botón.
   */
  deshabilitado: InputSignal<boolean> = input(false);

  /**
   * Evento que se emite cuando se hace clic en el botón. El evento emite un objeto PointerEvent que contiene información sobre el clic, como la posición del cursor y el elemento objetivo.
   * Este evento se puede escuchar desde el componente padre para ejecutar acciones específicas cuando se hace clic en el botón.
   */
  clic: OutputEmitterRef<PointerEvent> = output();

  /**
   * Emite evento cuando se da click en el botón. 
   * Sólo se emite cuando el botón está habilitado
   * @param evt Evento a emitir
   */
  onclick(evt: PointerEvent) {
    this.clic.emit(evt);
  }


}
