import { Directive, ElementRef } from '@angular/core';

/**
 * Directiva personalizada para resaltar elementos al pasar el mouse sobre ellos.
 */
@Directive({
  selector: '[appHighlight]',
  standalone: false,
  host: {
    '(mouseenter)': 'highlight()',
    '(mouseleave)': 'unsetHighlight()'
  }
})
export class HighlightDirective {

  /**
   *  Constructor de HighlightDirective
   * @param element Referencia al elemento DOM al que se aplica la directiva. Se utiliza para cambiar el estilo del elemento. 
   */
  constructor(private element: ElementRef) {
  }

  /**
   * Aplica un fondo amarillo al elemento cuando el mouse entra en él.
   */
  highlight() {
    this.setBackgroundColor("yellow");
  }

  /**
   * Elimina el fondo amarillo del elemento cuando el mouse sale de él.
   */
  unsetHighlight() {
    this.setBackgroundColor("");
  }

  /**
   *  Establece el color de fondo del elemento.
   * @param color Color de fondo a aplicar al elemento. Si se pasa una cadena vacía, se eliminará el fondo.
   */
  private setBackgroundColor(color: string): void {
    this.element.nativeElement.style.backgroundColor = color;
  }
}
