import { Directive, ElementRef, HostListener } from '@angular/core';

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
   * Constructor de la directvica Upper
   * @param elementRef Wrapper del elemento nativo
   */
  constructor(private elementRef: ElementRef) {
    const esElementoPermitido = this.elementRef.nativeElement instanceof HTMLInputElement
      || this.elementRef.nativeElement instanceof HTMLTextAreaElement;

    if (!esElementoPermitido) {
      throw new TypeError('El elemento debe ser un Input o TextArea')
    }

    this.elemento = this.elementRef.nativeElement;
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
      this.elemento.value = valorMayusculas;
      console.info("Valor transformado: ", valorMayusculas);
    }

  }

}
