import { Component, inject, Signal, signal, WritableSignal } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ValidationsService } from '@shared/services/validations/validations.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PaisesService } from '@services/paises/paises.service';
import { Pais } from '@shared/models/pais.model';
import { firstValueFrom, map, Observable, startWith, switchMap } from 'rxjs';
import { toSignal } from '@angular/core/rxjs-interop';
import { LoggerService } from '@shared/services/logger/logger.service';


@Component({
  selector: 'app-registro',
  standalone: false,
  templateUrl: './registro.component.html',
  styleUrl: './registro.component.scss',

})
export class RegistroComponent {
  private _formBuilder = inject(FormBuilder);
  paises: WritableSignal<Pais[]> = signal([] as Pais[]);
  paises$: Observable<Pais[]>;

  filtrados: Signal<Pais[] | undefined>;

  datosPersonalesForm: FormGroup = this._formBuilder.group({
    nombre: [null, [Validators.required]],
    apellidoPaterno: [null, [Validators.required]],
    apellidoMaterno: [null, [Validators.required]],
    fechaNacimiento: [null, [Validators.required]],
    pais: [null, [Validators.required]]
  });

  datosContactoForm: FormGroup = this._formBuilder.group({
    telefono: [null, [Validators.required]],
    email: [null, [Validators.required, ValidationsService.validateEmail]]
  });

  credencialesFormGroup: FormGroup = this._formBuilder.group({
    username: [null, [Validators.required, ValidationsService.validateUsername]],
    password: [null, [Validators.required, ValidationsService.validatePassword]]
  });



  get dpControls(): { [key: string]: AbstractControl } {
    return this.datosPersonalesForm.controls;
  }

  get nombreControl(): AbstractControl {
    const nombre = this.datosPersonalesForm.get("nombre");
    return nombre!;
  }
  get apellidoPaternoControl(): AbstractControl {
    const apellidoPaterno = this.datosPersonalesForm.get("apellidoPaterno");
    return apellidoPaterno!;
  }
  get apellidoMaternoControl(): AbstractControl {
    const apellidoMaterno = this.datosPersonalesForm.get("apellidoMaterno");
    return apellidoMaterno!;
  }
  get fechaNacimientoControl(): AbstractControl {
    const fechaNacimiento = this.datosPersonalesForm.get("fechaNacimiento");
    return fechaNacimiento!;
  }
  get paisControl(): AbstractControl {
    const pais = this.datosPersonalesForm.get("pais");
    return pais!;
  }



  get emailControl(): AbstractControl {
    const email = this.datosContactoForm.get("email");
    return email!;
  }
  get telefonoControl(): AbstractControl {
    const telefono = this.datosContactoForm.get("telefono");
    return telefono!;
  }


  get passwordControl(): AbstractControl {
    const password = this.credencialesFormGroup.get("password");
    return password!;
  }
  get usernameControl(): AbstractControl {
    const username = this.credencialesFormGroup.get("username");
    return username!;
  }

  constructor(
    private router: Router,
    private paisesService: PaisesService,
    private logger: LoggerService,
    private route: ActivatedRoute
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

  /**
   * Navega al login. Si hay cambios sin guardar en los formularios, solicita confirmación al usuario antes de navegar.
   */
  goToLogin(): void {
    if (this.datosPersonalesForm.dirty || this.datosContactoForm.dirty || this.credencialesFormGroup.dirty) {
      const confirmacion = confirm('¿Estás seguro de que deseas salir? Se perderán los datos ingresados.');
      if (!confirmacion) {
        return;
      }
    }

    this.router.navigate(['../login'], { relativeTo: this.route });
  }
}

