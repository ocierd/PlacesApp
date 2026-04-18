import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'home',
    pathMatch: 'full'
  },
  {
    path: 'home',
    loadChildren: () => import('@modules/main/home/home.module').then(m => m.HomeModule)
  },
  {
    path: 'visitas',
    loadChildren: () => import('@modules/main/visitas/visitas.module').then(m => m.VisitasModule)
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class MainRoutingModule { }
