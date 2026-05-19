import { Component, inject } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ValidationsService } from '@shared/services/validations/validations.service';

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

  datosContactoForm: FormGroup = this._formBuilder.group({
    telefono: [null, [Validators.required]],
    email: [null, [Validators.required, ValidationsService.validateEmail]]
  });



  get emailControl(): AbstractControl {
    const email = this.datosContactoForm.get("email");
    return email!;
  }
  get telefonoControl(): AbstractControl {
    const telefono = this.datosContactoForm.get("telefono");
    return telefono!;
  }

}
