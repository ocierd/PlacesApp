import { Component, inject } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ValidationsService } from '@shared/services/validations/validations.service';


/**
 * Paso para el ingreso de las credenciales del usuario
 */
@Component({
  selector: 'app-credenciales-registro-step',
  standalone: false,
  templateUrl: './credenciales-registro-step.component.html',
  styleUrl: './credenciales-registro-step.component.scss',
})
export class CredencialesRegistroStepComponent {

  private _formBuilder = inject(FormBuilder);

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

  constructor() {

  }

}
