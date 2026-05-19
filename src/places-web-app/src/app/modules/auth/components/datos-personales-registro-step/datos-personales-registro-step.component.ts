import { Component, inject, signal, Signal, WritableSignal } from '@angular/core';
import { toSignal } from '@angular/core/rxjs-interop';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PaisesService } from '@services/paises/paises.service';
import { Pais } from '@shared/models/pais.model';
import { LoggerService } from '@shared/services/logger/logger.service';
import { firstValueFrom, map, Observable, startWith, switchMap } from 'rxjs';

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
  paises: WritableSignal<Pais[]> = signal([] as Pais[]);
  paises$: Observable<Pais[]>;
  filtrados: Signal<Pais[] | undefined>;

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

    this.paises$ = this.paisesService.getPaises();

    this.filtrados = toSignal(this.paises$
      .pipe(switchMap(paises => this.paisControl
        .valueChanges.pipe(
          startWith(''),
          map(value => this.filtrarPaises(value))
        ))
      ));

    this.initPaises();
  }


  /**
 * Filtra la lista de países según el valor ingresado en el campo de selección. 
 * Si el valor es un objeto Pais, devuelve ese país. Si el valor es una cadena vacía, devuelve los países con ID menor a 10. De lo contrario, devuelve los países cuyo nombre incluye el valor ingresado (ignorando mayúsculas).
 * @param value Valor ingresado en el campo de selección (puede ser una cadena o un objeto Pais)
 * @returns Lista de países filtrados según el valor ingresado
 */
  filtrarPaises(value: string | Pais): Pais[] {
    if (value && typeof value === 'object' && 'nombre' in value) {
      return [value];
    }
    const filterValue = value.toLowerCase();
    if (!filterValue) {
      const filtrados = this.paises().filter(p => p.paisId < 10); // Devuelve todos los países si el valor de búsqueda está vacío
      return filtrados;
    }
    return this.paises().filter(pais => pais.nombre.toLowerCase().includes(filterValue));
  }

  /**
   * Inicializa la lista de países obteniéndolos del servicio PaisesService. 
   * Maneja errores y registra el proceso de carga en el LoggerService.
   */
  async initPaises(): Promise<void> {
    try {
      const p = await firstValueFrom(this.paises$);
      this.paises.set(p);
    } catch (error) {
      this.logger.error('Error al cargar los países:', error);
      alert('No se pudieron cargar los países. Por favor, inténtalo de nuevo más tarde.');
    }
    finally {
      this.logger.info('Proceso de carga de países finalizado');
    }
  }

  /**
   * Función para mostrar el nombre del país en el campo de selección.
   * @param pais Pais seleccionado
   * @returns Nombre a mostrar
   */
  displayPais(pais: Pais | null): string {
    return pais ? pais.nombre : '';
  }

}
