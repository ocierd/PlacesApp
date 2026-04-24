import { Directive, ElementRef, Optional } from '@angular/core';
import { AbstractControl, NgControl } from '@angular/forms';

/**
 * Directiva utilizada para convertir a minúsculas el texto ingresado.
 * Es necesario que la directiva sea utilizada en "input" o "textarea"
 */
@Directive({
  selector: '[appLower]',
  standalone: false,
  host: {
    '(input)': 'aMinusculas()'
  }
})
export class LowerDirective {


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
   * Intenta realizar el cambio a minúsculas del elemento nativo y del control
   */
  aMinusculas() {
    const valorElemento = this.elemento.value;

    if (typeof valorElemento === 'string') {
      console.info('Valor original: ', valorElemento);
      const valorMinusculas = valorElemento.toLowerCase();
      console.info("Valor transformado: ", valorMinusculas);
      this.cambiaraMinusculasElementoNativo(valorMinusculas);
      this.cambiarMinusculasControl(valorMinusculas);
    }

  }

  /**
   * Cambia el valor del elemento nativo a minúsculas
   * @param valorMayusculas Valor en minúsculas
   */
  cambiaraMinusculasElementoNativo(valorMayusculas: string) {
    this.elemento.value = valorMayusculas;
  }

  /**
   * Cambia el valor del control a minúsculas, siempre y cuando éste exista
   * @param valorMayusculas Valor en minúsculas
   */
  cambiarMinusculasControl(valorMayusculas: string) {
    if (this.control) {
      this.control.setValue(valorMayusculas, { emitEvent: false });
    }
  }



}
