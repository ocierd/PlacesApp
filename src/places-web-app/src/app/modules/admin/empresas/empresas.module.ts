import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EmpresasRoutingModule } from './empresas-routing-module';
import { RegistroComponent } from '@modules/admin/empresas/pages/registro/registro.component';
import { SharedModule } from '@shared/shared.module';

@NgModule({
  declarations: [RegistroComponent],
  imports: [CommonModule, EmpresasRoutingModule, SharedModule],
})
export class EmpresasModule {}
