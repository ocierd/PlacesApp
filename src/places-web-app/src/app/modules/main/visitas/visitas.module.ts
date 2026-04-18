import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { VisitasRoutingModule } from './visitas-routing.module';
import { ListaVisitasComponent } from './pages/lista-visitas/lista-visitas.component';

@NgModule({
  declarations: [ListaVisitasComponent],
  imports: [CommonModule, VisitasRoutingModule],
})
export class VisitasModule {}
