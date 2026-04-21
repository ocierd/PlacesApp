import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';

/**
 * Componente para login
 */
@Component({
  selector: 'app-login',
  standalone: false,
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {

  /**
   * Formulario login
   */
  loginForm: FormGroup;

  /**
   * Accede a propiedad valid de "username". Indica si el usuario es válido
   */
  get usernameValid(): boolean {
    const usernameControl: AbstractControl | null = this.loginForm.get('username');
    if (usernameControl == null) {
      return false;
    }
    return usernameControl.valid;
  }

  /**
   * Accede a propiedad valid de "password". Indica si la contraseña es válido
   */
  get passwordValid(): boolean {
    const passwordControl: AbstractControl | null = this.loginForm.get('password');
    if (passwordControl === null) {
      return false;
    }
    return passwordControl.valid;
  }

  /**
   * Constructor de LoginComponent
   * @param fb Form builder
   */
  constructor(private fb: FormBuilder) {
    this.loginForm = this.fb.group({
      username: [null, Validators.required],
      password: [null, Validators.required]
    })

  }


  login() {
    const datosLogin = this.loginForm.getRawValue();
    console.log(datosLogin);

  }

}
