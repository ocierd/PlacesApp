import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'empresa',
    pathMatch: 'full'
  },
  {
    path: 'empresa',
    loadChildren: () => import('@modules/admin/empresas/empresas.module').then(m => m.EmpresasModule)
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AdminRoutingModule {}
