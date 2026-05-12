import { Component, inject, OnInit, Signal, signal, WritableSignal } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators, FormControl } from '@angular/forms';
import { ValidationsService } from '@shared/services/validations/validations.service';
import { Router } from '@angular/router';
import { PaisesService } from '@services/paises/paises.service';
import { Pais } from '@shared/models/pais.model';
import { delay, firstValueFrom, map, startWith } from 'rxjs';
import { toSignal } from '@angular/core/rxjs-interop';
import { LoggerService } from '@shared/services/logger/logger.service';
@Component({
  selector: 'app-registro',
  standalone: false,
  templateUrl: './registro.component.html',
  styleUrl: './registro.component.scss',

})
export class RegistroComponent implements OnInit {
  private _formBuilder = inject(FormBuilder);
  paises: WritableSignal<Pais[]> = signal([] as Pais[]);

  myControl = new FormControl();

  filtrados: Signal<Pais[] | undefined>;

  firstFormGroup: FormGroup = this._formBuilder.group({
    nombre: [null, [Validators.required]],
    apellidoPaterno: [null, [Validators.required]],
    apellidoMaterno: [null, [Validators.required]],
    fechaNacimiento: [null, [Validators.required]],
    pais: [null, [Validators.required]]
  });
  secondFormGroup: FormGroup = this._formBuilder.group({
    telefono: [null, [Validators.required]],
    email: [null, [Validators.required, ValidationsService.validateEmail]]
  });

  credencialesFormGroup: FormGroup = this._formBuilder.group({
    username: [null, [Validators.required, ValidationsService.validateUsername]],
    password: [null, [Validators.required, ValidationsService.validatePassword]]
  });



  get passwordControl(): AbstractControl {
    const password = this.credencialesFormGroup.get("password");
    return password!;
  }
  get usernameControl(): AbstractControl {
    const username = this.credencialesFormGroup.get("username");
    return username!;
  }
  get nombreControl(): AbstractControl {
    const nombre = this.firstFormGroup.get("nombre");
    return nombre!;
  }

  get apellidoPaternoControl(): AbstractControl {
    const apellidoPaterno = this.firstFormGroup.get("apellidoPaterno");
    return apellidoPaterno!;
  }
  get apellidoMaternoControl(): AbstractControl {
    const apellidoMaterno = this.firstFormGroup.get("apellidoMaterno");
    return apellidoMaterno!;
  }
  get fechaNacimientoControl(): AbstractControl {
    const fechaNacimiento = this.firstFormGroup.get("fechaNacimiento");
    return fechaNacimiento!;
  }

  get paisControl(): AbstractControl {
    const pais = this.firstFormGroup.get("pais");
    return pais!;
  }
  get emailControl(): AbstractControl {
    const email = this.secondFormGroup.get("email");
    return email!;
  }
  get telefonoControl(): AbstractControl {
    const telefono = this.secondFormGroup.get("telefono");
    return telefono!;
  }

  constructor(
    private router: Router,
    private paisesService: PaisesService,
    private logger: LoggerService
  ) {
    this.initPaises();

    this.filtrados = toSignal(this.myControl
      .valueChanges.pipe(
        startWith(''),
        map(value => this.filtrarPaises(value))
      ));
  }


  filtrarPaises(value: string): Pais[] {
    const filterValue = value.toLowerCase();
    if (!filterValue) {
      console.log("Entró al valor vacío");

      const filtrados = this.paises().filter(p => p.paisId < 10); // Devuelve todos los países si el valor de búsqueda está vacío
      console.log(filtrados);

      return filtrados;
    }
    return this.paises().filter(pais => pais.nombre.toLowerCase().includes(filterValue));
  }


  ngOnInit(): void {

  }
  async initPaises(): Promise<void> {
    try {
      const p = await firstValueFrom(this.paisesService.getPaises());
      this.logger.info('Paises cargados:', p);
      this.paises.set(p);
    } catch (error) {
      this.logger.error('Error al cargar los países:', error);
    }
    finally {
      this.logger.info('Proceso de carga de países finalizado');
    }
  }
}

