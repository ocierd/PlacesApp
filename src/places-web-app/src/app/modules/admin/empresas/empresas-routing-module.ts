import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { RegistroComponent } from '@modules/admin/empresas/pages/registro/registro.component';
import { CatalogoComponent } from '@modules/admin/empresas/pages/catalogo/catalogo.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'registro',
    pathMatch: 'full'
  },
  {
    path: 'registro/:id',
    component: RegistroComponent
  },
  {
    path: 'catalogo',
    component: CatalogoComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class EmpresasRoutingModule {
  
}