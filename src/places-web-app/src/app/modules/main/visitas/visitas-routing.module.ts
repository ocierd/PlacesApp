import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListaVisitasComponent } from './pages/lista-visitas/lista-visitas.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'lista-visitas',
    pathMatch: 'full'
  },
  {
    path: 'lista-visitas',
    component: ListaVisitasComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class VisitasRoutingModule { }
