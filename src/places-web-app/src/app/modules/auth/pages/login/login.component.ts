import { Component, signal } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthService } from '../../../../core/services/auth/auth-service';
import { LoginData } from '@shared/models/login.model';
import { firstValueFrom } from 'rxjs';
import { HttpErrorResponse } from '@angular/common/http';
import { LoggerService } from '@shared/services/logger/logger.service';
import { Router } from '@angular/router';
import { ValidationsService } from '@shared/services/validations/validations.service';
import { DialogService } from '@shared/services/dialog/dialog.service';
import { ModulosDialogComponent } from '@modules/auth/components/modulos-dialog/modulos-dialog.component';
import { ModulosService } from '@services/modulos/modulos.service';
import { ModuloDto } from '@shared/models/modulo.model';

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
    private router: Router,
    private dialogService: DialogService,
    private modulosService: ModulosService,
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


  /**
   * Realiza el proceso de login utilizando los datos ingresados en el formulario.
   * Si el login es exitoso, se obtiene un token de autenticación que se almacena utilizando el servicio de autenticación. 
   * Luego, se muestra una selección de módulos disponibles para el usuario. Si ocurre un error durante el proceso de login, se maneja el error mostrando mensajes adecuados al usuario.
   */
  async login(): Promise<void> {
    try {

      this.cargando.set(true)
      const datosLogin = this.loginForm.getRawValue() as LoginData;
      this.logger.log(datosLogin);
      const loginProm = firstValueFrom(this.authService.auth(datosLogin));
      const tokenData = await loginProm;
      this.logger.log(tokenData);
      this.authService.setToken(tokenData);
      // this.router.navigate(['main']);
      await this.displayModulesSelection();
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

  /**
   * Muestra un diálogo para seleccionar el módulo al que se desea acceder después de un login exitoso.
   * Si el usuario tiene acceso a un solo módulo, se redirige automáticamente a ese módulo sin mostrar el diálogo. 
   * Si el usuario tiene acceso a múltiples módulos, se muestra un diálogo con la lista de módulos disponibles para que el usuario seleccione uno. 
   * Después de que el usuario selecciona un módulo, se redirige a la ruta correspondiente al módulo seleccionado. Si no se selecciona ningún módulo, se muestra una alerta indicando que no se seleccionó ningún módulo.
   */
  async displayModulesSelection(): Promise<void> {
    const modulos = await firstValueFrom(this.modulosService.getModulosPorUsuario());
    
    if (modulos.length === 0) {
      this.logger.warn('No hay módulos disponibles para el usuario.');
      this.modulosService.setSelectedModulo(null);
      alert("No hay módulos disponibles para el usuario.");
      this.authService.clearToken();
      return;
    }
    if (modulos.length === 1) {
      const onlyModulo = modulos[0];
      this.logger.info('Redirigiendo al único módulo disponible:', onlyModulo);
      this.modulosService.setSelectedModulo(onlyModulo);
      this.router.navigate([onlyModulo.ruta]);
      return;
    }

    const config = { data: modulos, width: '800px', height: '800px', disableClose: true, autoFocus: true, hasBackdrop: true };
    const openedObs = this.dialogService.openDialog<ModulosDialogComponent, ModuloDto[], ModuloDto | null>(ModulosDialogComponent, config);
    const selected = await firstValueFrom(openedObs.afterClosed());
    if (selected) {
      this.logger.info('Módulo seleccionado:', selected);
      this.modulosService.setSelectedModulo(selected);
      this.router.navigate([selected.ruta]);
    } else {
      this.logger.warn('No se seleccionó ningún módulo.');
      this.modulosService.setSelectedModulo(null);
      this.authService.clearToken();
      alert("No se seleccionó ningún módulo.");
    }
  }

}
