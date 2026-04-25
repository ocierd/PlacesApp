import { Component, Input } from '@angular/core';

/**
 * Componente para mostrar un spinner de carga
 */
@Component({
  selector: 'app-spinner',
  standalone: false,
  templateUrl: './spinner.component.html',
  styleUrl: './spinner.component.scss',
})
export class SpinnerComponent {

  /**
   * Diámetro del spinner. Por defecto 30
   */
  @Input()
  diametro: number = 30;

}
