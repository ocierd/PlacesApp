import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DatosPersonalesRegistroStepComponent } from '@modules/auth/components/datos-personales-registro-step/datos-personales-registro-step.component';
import { DatosContactoRegistroStepComponent } from '@modules/auth/components/datos-contacto-registro-step/datos-contacto-registro-step.component';
import { CredencialesRegistroStepComponent } from '@modules/auth/components/credenciales-registro-step/credenciales-registro-step.component';
import { Usuario, UsuarioRegistroDto } from '@shared/models/usuario.model';
import { LoggerService } from '@shared/services/logger/logger.service';
import { HttpErrorResponse } from '@angular/common/http';
import { UsuarioService } from '@services/usuario/usuario.service';
import { firstValueFrom } from 'rxjs';
import { Pais } from '@shared/models/pais.model';
import { AlertType } from '@shared/models/ui/alert.model';
import { AlertsService } from '@shared/services/alerts/alerts.service';
import { ValidacionError } from '@shared/models/errors.model';
import { ErrorsUtils } from '@shared/utils/errors-utils';
import { environment } from '@envs/environment';


/**
 * Componente principal para el registro del usuario
 */
@Component({
  selector: 'app-registro',
  standalone: false,
  templateUrl: './registro.component.html',
  styleUrl: './registro.component.scss',


})
export class RegistroComponent implements AfterViewInit, OnInit {


  @ViewChild('datosPersonalesStep')
  datosPersonalesStep!: DatosPersonalesRegistroStepComponent

  @ViewChild('datosContactoStep')
  datosContactoStep!: DatosContactoRegistroStepComponent

  @ViewChild('credencialesStep')
  credencialesStep!: CredencialesRegistroStepComponent



  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private logger: LoggerService,
    private usuarioService: UsuarioService,
    private alertService: AlertsService
  ) { }

  /**
   * Navega al login. Si hay cambios sin guardar en los formularios, solicita confirmación al usuario antes de navegar.
   */
  goToLogin(): void {
    if (
      this.datosContactoStep.datosContactoForm.dirty ||
      this.datosPersonalesStep.datosPersonalesForm.dirty ||
      this.credencialesStep.credencialesFormGroup.dirty) {
      const confirmacion = confirm('¿Estás seguro de que deseas salir? Se perderán los datos ingresados.');
      if (!confirmacion) {
        return;
      }
    }

    this.router.navigate(['../login'], { relativeTo: this.route });
  }

  ngOnInit(): void {
    this.logger.info("en ONINIT: ",this.datosContactoStep);
  }
/**
 * Se utiliza para realizar pruebas y no se tenga que estar capturando la información.
 * Se llama una vez que los componentes internos han sido inicializados. 
*/
  ngAfterViewInit(): void {
    this.logger.info("en ngAfterViewInit: ",this.datosContactoStep);
    if(!environment.isProd){
      this.datosPersonalesStep.datosPersonalesForm.patchValue({
        nombre:'Fernando',
        apellidoPaterno:'Ricardo'
      });
      this.datosContactoStep.datosContactoForm.patchValue({
        telefono:'5541943983',
        email:'ocierd@msn.com'
      });
    }
    
  }
/**
 * Obtención de datos para la creación del usuario. 
 * Los datos se obtinen desde los formularios de los componentes de las pasos (stepper)
 */
  async crearUsuario(): Promise<void> {
    try {
      const datosContacto: {
        telefono: string,
        email: string,
        pais: Pais
      } = this.datosContactoStep.datosContactoForm.getRawValue();

      const credencialesData: {
        username: string, password: string
      } = this.credencialesStep.credencialesFormGroup.getRawValue();

      const datosPersonales: {
        nombre: string, apellidoPaterno: string, apellidoMaterno: string, fechaNacimiento: string
      } = this.datosPersonalesStep.datosPersonalesForm.getRawValue();

      const nuevoRegistro = {
        ...credencialesData,
        ...datosContacto,
        ...datosPersonales,
        paisId: datosContacto.pais.paisId
      } as UsuarioRegistroDto;

       await firstValueFrom(
        this.usuarioService.crearUsuario(nuevoRegistro)
      );

      this.alertService.sendSuccessAlert('El registro fue generado correctamente.');
      this.router.navigate(['../login'], { relativeTo: this.route });
    } catch (error) {

      this.logger.error("Ocurrió un error", error);
      const errorMsg = ErrorsUtils.getValidacionError(error);
      this.alertService.sendErrorAlert(errorMsg);

    } 
    finally {
      
    }
  }
}

