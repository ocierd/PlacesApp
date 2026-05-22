import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EmpresasRoutingModule } from './empresas-routing-module';
import { RegistroComponent } from './pages/registro/registro.component';
import { SharedModule } from '@shared/shared.module';
import { CatalogoComponent } from './pages/catalogo/catalogo.component';

@NgModule({
  declarations: [RegistroComponent, CatalogoComponent],
  imports: [CommonModule, EmpresasRoutingModule, SharedModule],
})
export class EmpresasModule {}
