import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { AuthRoutingModule } from './auth-routing.module';
import { LoginComponent } from './pages/login/login.component';
import { RegistroComponent } from './pages/registro/registro.component';
import { SharedModule } from '@shared/shared.module';
import { DatosPersonalesRegistroStepComponent } from './components/datos-personales-registro-step/datos-personales-registro-step.component';
import { DatosContactoRegistroStepComponent } from './components/datos-contacto-registro-step/datos-contacto-registro-step.component';
import { CredencialesRegistroStepComponent } from './components/credenciales-registro-step/credenciales-registro-step.component';
import { ModulosDialogComponent } from './components/modulos-dialog/modulos-dialog.component';
import { A11yModule } from "@angular/cdk/a11y";

@NgModule({
  declarations: [
    LoginComponent,
    RegistroComponent,
    DatosPersonalesRegistroStepComponent,
    DatosContactoRegistroStepComponent,
    CredencialesRegistroStepComponent,
    ModulosDialogComponent,
  ],
  imports: [CommonModule, AuthRoutingModule, SharedModule,  A11yModule],
})
export class AuthModule {}
