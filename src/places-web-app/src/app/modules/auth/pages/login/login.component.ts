import { Component, signal } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../../../core/services/auth/auth-service';
import { LoginData } from '@shared/models/login.model';
import { firstValueFrom } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import { LoggerService } from '@shared/services/logger/logger.service';
import { Router } from '@angular/router';

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
    private authService: AuthService,
    private logger: LoggerService,
    private router:Router
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
      this.logger.log(datosLogin);
      const loginProm = firstValueFrom(this.authService.auth(datosLogin));
      const tokenData = await loginProm;
      this.logger.log(tokenData);
      this.authService.setToken(tokenData);
      this.router.navigate(['main']);
    } catch (error) {
      this.logger.error("Error en login: ", error);
      if (error instanceof HttpErrorResponse) {

        if (error.status === 401) {
          alert("Usuario o contraseña incorrectos");
          this.loginForm.get("password")?.reset();
        }
        else {
          alert("Error: " + error.message);
        }

      } else {

        this.logger.error("Error: ", error);
      }

    }
    finally {
      this.cargando.set(false);
      this.logger.log("SE cambio el valor CARGANDO");
    }

  }

}
