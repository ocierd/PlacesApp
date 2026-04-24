import { Directive, ElementRef, Optional } from '@angular/core';
import { AbstractControl, NgControl } from '@angular/forms';

/**
 * Directiva utilizada para convertir a mayúsculas el texto ingresado.
 * Es necesario que la directiva sea utilizada en "input" o "textarea"
 */
@Directive({
  selector: '[appUpper]',
  standalone: false,
  host: {
    '(input)': 'cambiarAMayusculas($event)',
  }
})
export class UpperDirective {

  /**
   * Elemento nativo en donde se usa la directiva
   */
  private elemento: HTMLInputElement | HTMLTextAreaElement;

  /**
   * Control en el que se usa la directiva
   */
  private control: AbstractControl | null = null;

  /**
   * Constructor de la directvica Upper
   * @param elementRef Wrapper del elemento nativo
   */
  constructor(private elementRef: ElementRef, @Optional() private formControl: NgControl) {
    const esElementoPermitido = this.elementRef.nativeElement instanceof HTMLInputElement
      || this.elementRef.nativeElement instanceof HTMLTextAreaElement;

    if (!esElementoPermitido) {
      throw new TypeError('El elemento debe ser un Input o TextArea');
    }

    this.elemento = this.elementRef.nativeElement;

    if (this.formControl && this.formControl.control) {
      this.control = this.formControl.control;
    }

  }

  /**
   * Cambia el texto de minúsculas a mayúsculas, siempre y cuando el valor (value) sea del tipo "string"
   * @param evento Evento que se dispara
   */
  cambiarAMayusculas(evento: Event) {
    console.log("Tipo evento: ", evento.type);
    const valorElemento = this.elemento.value;

    if (typeof valorElemento === 'string') {
      console.info('Valor original: ', valorElemento);
      const valorMayusculas = valorElemento.toUpperCase();
      console.info("Valor transformado: ", valorMayusculas);
      this.cambiaraMayusculasElementoNativo(valorMayusculas);
      this.cambiarMayusculasControl(valorMayusculas);
    }

  }

  /**
   * Cambia el valor del elemento nativo a mayúsculas
   * @param valorMayusculas Valor en mayúsculas
   */
  cambiaraMayusculasElementoNativo(valorMayusculas: string) {
    this.elemento.value = valorMayusculas;
  }

  /**
   * Cambia el valor del control a mayúsculas, siempre y cuando éste exista
   * @param valorMayusculas Valor en mayúsculas
   */
  cambiarMayusculasControl(valorMayusculas: string) {
    if (this.control) {
      this.control.setValue(valorMayusculas, { emitEvent: false });
    }
  }




}
