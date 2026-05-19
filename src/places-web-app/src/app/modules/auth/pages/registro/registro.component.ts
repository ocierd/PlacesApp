import { Component, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DatosPersonalesRegistroStepComponent } from '@modules/auth/components/datos-personales-registro-step/datos-personales-registro-step.component';
import { DatosContactoRegistroStepComponent } from '@modules/auth/components/datos-contacto-registro-step/datos-contacto-registro-step.component';
import { CredencialesRegistroStepComponent } from '@modules/auth/components/credenciales-registro-step/credenciales-registro-step.component';


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
    private route: ActivatedRoute
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
}

