import { Component, inject , signal, WritableSignal } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ValidationsService } from '@shared/services/validations/validations.service';
import { PaisesService } from '@services/paises/paises.service';
import { Pais } from '@shared/models/pais.model';
import { LoggerService } from '@shared/services/logger/logger.service';
import { debounceTime, firstValueFrom, startWith, tap } from 'rxjs';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';

/**
 * Paso de registro de datos de contacto
 */
@Component({
  selector: 'app-datos-contacto-registro-step',
  standalone: false,
  templateUrl: './datos-contacto-registro-step.component.html',
  styleUrl: './datos-contacto-registro-step.component.scss',
})
export class DatosContactoRegistroStepComponent {
  private _formBuilder = inject(FormBuilder);
  protected filtrados: WritableSignal<Pais[]> = signal([] as Pais[]);

  datosContactoForm: FormGroup = this._formBuilder.group({
    telefono: [null, [Validators.required]],
    email: [null, [Validators.required, ValidationsService.validateEmail]],
    pais: [null, [Validators.required]]
  });



  get emailControl(): AbstractControl {
    const email = this.datosContactoForm.get("email");
    return email!;
  }
  get telefonoControl(): AbstractControl {
    const telefono = this.datosContactoForm.get("telefono");
    return telefono!;
  }

    get paisControl(): AbstractControl {
    return this.datosContactoForm.get("pais")!;
  }
    constructor(private paisesService: PaisesService,
      private logger: LoggerService
    ) {
      this.paisControl
        .valueChanges.pipe(
          startWith(''),
          debounceTime(300),
          tap(value => this.filtrarPaises(value)),
          takeUntilDestroyed()
        )
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
