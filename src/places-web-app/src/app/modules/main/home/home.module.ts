import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { MainHomeComponent } from './pages/main-home/main-home.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { SharedModule } from '@shared/shared.module';
import { ButtonsComponent } from './pages/buttons/buttons.component';
import { AutocompleteExampleComponent } from './pages/autocomplete-example/autocomplete-example.component';

@NgModule({
  declarations: [
    MainHomeComponent,
    DashboardComponent,
    ButtonsComponent,
    AutocompleteExampleComponent,
  ],
  imports: [CommonModule, HomeRoutingModule, SharedModule],
})
export class HomeModule {}
