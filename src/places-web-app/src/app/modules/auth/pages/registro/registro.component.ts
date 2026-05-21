import { Component, ViewChild } from '@angular/core';
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


/**
 * Componente principal para el registro del usuario
 */
@Component({
  selector: 'app-registro',
  standalone: false,
  templateUrl: './registro.component.html',
  styleUrl: './registro.component.scss',

})
export class RegistroComponent {


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
    private usuarioService: UsuarioService
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



  async crearUsuario(): Promise<void> {
    try {
      const datosContacto:{
        telefono:string,
        email:string,
        pais:Pais
      }=this.datosContactoStep.datosContactoForm.getRawValue();

      const nuevoRegistro = {
        ...this.credencialesStep.credencialesFormGroup.getRawValue(),
        ...datosContacto,
        ...this.datosPersonalesStep.datosPersonalesForm.getRawValue(),
        paisId:datosContacto.pais.paisId
      } as UsuarioRegistroDto

      console.log(nuevoRegistro)

      const usuario = await firstValueFrom(
        this.usuarioService.crearUsuario(nuevoRegistro)
      );

      console.log("Usuario creado:", usuario);

    } catch (error) {

      this.logger.error("Error en registro:", error);

      if (error instanceof HttpErrorResponse) {

        alert("Error: " + error.message);

      } else {

        this.logger.error("Error desconocido:", error);

      }

    } finally {

    }
  }
}

