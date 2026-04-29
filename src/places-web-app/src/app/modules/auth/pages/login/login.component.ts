import { Component, signal } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../../../core/services/auth/auth-service';
import { LoginData } from '@shared/models/login.model';
import { firstValueFrom } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import { LoggerService } from '@shared/services/logger/logger.service';
import { Router } from '@angular/router';
import { ValidationsService } from '@shared/services/validations/validations.service';

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
   * Accede al control "username" del formulario de login. 
   * Se utiliza para mostrar mensajes de error específicos para este campo en la plantilla. Si el control no se encuentra, se lanza un error. Esto garantiza que el código que accede a este control siempre obtenga una instancia válida de AbstractControl o reciba una notificación clara de que el control no existe.
   */
  get usernameControl(): AbstractControl {
    return this.getControlByName('username');
  }

  /**
   * Accede al control "password" del formulario de login. 
   * Se utiliza para mostrar mensajes de error específicos para este campo en la plantilla. Si el control no se encuentra, se lanza un error. Esto garantiza que el código que accede a este control siempre obtenga una instancia válida de AbstractControl o reciba una notificación clara de que el control no existe.
   */
  get passwordControl(): AbstractControl {
    return this.getControlByName('password');
  }



  /**
   * Accede a propiedad valid de "username". Indica si el usuario es válido
   */
  get usernameValid(): boolean {
    return this.usernameControl.valid;
  }

  /**
   * Accede a propiedad valid de "password". Indica si la contraseña es válido
   */
  get passwordValid(): boolean {
    return this.passwordControl.valid;
  }

  /**
   * Constructor de LoginComponent
   * @param fb Form builder
   */
  constructor(private fb: FormBuilder,
    private authService: AuthService,
    private logger: LoggerService,
    private router: Router
  ) {
    this.loginForm = this.fb.group({
      username: [null, [Validators.required, ValidationsService.validateUsername]],
      password: [null, [Validators.required, ValidationsService.validatePassword]]
    })


  }


  /**
   * Obtiene un control del formulario de login por su nombre.
   * Si el control no se encuentra, se lanza un error. Esto garantiza que el código que accede a este control siempre obtenga una instancia válida de AbstractControl o reciba una notificación clara de que el control no existe.
   * @param name Nombre del control
   * @returns Control dentro del formulario
   */
  private getControlByName(name: string): AbstractControl {
    const control: AbstractControl | null = this.loginForm.get(name);
    if (!control) {
      throw new Error(`Control '${name}' no encontrado en el formulario`);
    }
    return control;
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
          this.passwordControl.reset();
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
    }

  }

}
