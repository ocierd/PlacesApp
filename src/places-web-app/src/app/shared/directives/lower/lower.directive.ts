import { Directive, ElementRef } from '@angular/core';

@Directive({
  selector: '[appLower]',
  standalone: false,
  host: {
    '(input)': 'aMinusculas()'
  }
})
export class LowerDirective {

  private elementoNativo: HTMLInputElement | HTMLTextAreaElement;

  constructor(private elementRef: ElementRef) {
    const esValido = elementRef.nativeElement instanceof HTMLInputElement
      || elementRef.nativeElement instanceof HTMLTextAreaElement;

    if (!esValido) {
      throw new TypeError(`El elemento que usa la directiva no está permitido.
          Solo se puede usar en Input o TextArea`);
    }
    this.elementoNativo = elementRef.nativeElement;
  }


  aMinusculas() {
    const valorInicial = this.elementoNativo.value;
    if (typeof valorInicial === 'string') {
      const valorMinusculas= valorInicial.toLowerCase();
      this.elementoNativo.value = valorMinusculas;
    }

  }


}
