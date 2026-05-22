import { Component, inject, signal, WritableSignal } from '@angular/core';
import { toSignal } from '@angular/core/rxjs-interop';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';


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

 

  public datosPersonalesForm: FormGroup = this._formBuilder.group({
    nombre: [null, [Validators.required]],
    apellidoPaterno: [null, [Validators.required]],
    apellidoMaterno: [null],
    fechaNacimiento: [null, [Validators.required]]
  });

  get dpControls(): { [key: string]: AbstractControl } {
    return this.datosPersonalesForm.controls;
  }




}
