import { Component, inject, signal, WritableSignal } from '@angular/core';
import { toSignal } from '@angular/core/rxjs-interop';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PaisesService } from '@services/paises/paises.service';
import { Pais } from '@shared/models/pais.model';
import { LoggerService } from '@shared/services/logger/logger.service';
import { debounceTime, firstValueFrom, map, Observable, startWith, tap } from 'rxjs';

/**
 * Paso para el registro de los datos personales
 */
@Component({
  selector: 'app-datos-personales-registro-step',
  standalone: false,
  templateUrl: './datos-personales-registro-step.component.html',
  styleUrl: './datos-personales-registro-step.component.scss',
})
export class DatosPersonalesRegistroStepComponent {
  private _formBuilder = inject(FormBuilder);

  protected filtrados: WritableSignal<Pais[]> = signal([] as Pais[]);

  public datosPersonalesForm: FormGroup = this._formBuilder.group({
    nombre: [null, [Validators.required]],
    apellidoPaterno: [null, [Validators.required]],
    apellidoMaterno: [null, [Validators.required]],
    fechaNacimiento: [null, [Validators.required]],
    pais: [null, [Validators.required]]
  });

  get dpControls(): { [key: string]: AbstractControl } {
    return this.datosPersonalesForm.controls;
  }

  get paisControl(): AbstractControl {
    return this.datosPersonalesForm.get("pais")!;
  }

  constructor(private paisesService: PaisesService,
    private logger: LoggerService
  ) {
    this.paisControl
      .valueChanges.pipe(
        startWith(''),
        debounceTime(300),
        tap(value => this.filtrarPaises(value)))
      .subscribe();

  }


  /**
 * Filtra la lista de países según el valor ingresado en el campo de selección. 
 * Si el valor es un objeto Pais, devuelve ese país. Si el valor es una cadena vacía, devuelve los países con ID menor a 10. De lo contrario, devuelve los países cuyo nombre incluye el valor ingresado (ignorando mayúsculas).
 * @param value Valor ingresado en el campo de selección (puede ser una cadena o un objeto Pais)
 * @returns Lista de países filtrados según el valor ingresado
 */
  async filtrarPaises(value: string | Pais): Promise<void> {
    this.logger.info("Se está filtrando la lista de países con el valor:", value);
    if (value && typeof value === 'object' && 'nombre' in value) {
      return;
    }
    const filterValue = value.toLowerCase();
    const paises = await firstValueFrom(this.paisesService.buscarPaises(filterValue));
    this.filtrados.set(paises);
  }



  /**
   * Función para mostrar el nombre del país en el campo de selección.
   * @param pais Pais seleccionado
   * @returns Nombre a mostrar
   */
  displayPais(pais: Pais | null): string {
    if (pais && typeof pais === 'object' && 'nombre' in pais) {
      return `${pais.codigo} - ${pais.nombre}`;
    }
    return '';
  }

}
