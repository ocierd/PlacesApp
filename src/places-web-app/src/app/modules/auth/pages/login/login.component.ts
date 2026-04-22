import { Component, signal } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../../../core/services/auth/auth-service';
import { LoginData } from '@shared/models/login.model';
import { firstValueFrom } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';

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
   * Indica si se está haciendo "login"
   */
  cargando = signal(false);

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
  constructor(private fb: FormBuilder,
    private authService: AuthService
  ) {
    this.loginForm = this.fb.group({
      username: [null, Validators.required],
      password: [null, Validators.required]
    })

  }


  async login(): Promise<void> {
    try {
      this.cargando.set(true)
      const datosLogin = this.loginForm.getRawValue() as LoginData;
      console.log(datosLogin);
      const loginProm = firstValueFrom(this.authService.auth(datosLogin));
      const tokenData = await loginProm;
      console.log(tokenData);

    } catch (error) {
      console.error("Error en login: ", error);
      if (error instanceof HttpErrorResponse) {

          if(error.status === 401){
            alert("Usuario o contraseña incorrectos");
            this.loginForm.get("password")?.reset();
          }
          else{
            alert("Error: " + error.message);
          }
          
      } else {

        console.error("Error: ", error);
      }

    }
    finally {
      this.cargando.set(false);
      console.log("SE cambio el valor CARGANDO");
    }

  }

}
