import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainHomeComponent } from './pages/main-home/main-home.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { ButtonsComponent } from './pages/buttons/buttons.component';
import { AutocompleteExampleComponent } from './pages/autocomplete-example/autocomplete-example.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'overview',
    pathMatch: 'full'
  },
  {
    path: 'overview',
    component: MainHomeComponent
  },
  {
    path: 'dashboard',
    component: DashboardComponent
  },
  {
    path: 'buttons',
    component: ButtonsComponent
  },
  {
    path: 'autocomplete',
    component: AutocompleteExampleComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class HomeRoutingModule { }
