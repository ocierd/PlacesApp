import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomeRoutingModule } from './home-routing.module';
import { MainHomeComponent } from './pages/main-home/main-home.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';

@NgModule({
  declarations: [MainHomeComponent, DashboardComponent],
  imports: [CommonModule, HomeRoutingModule],
})
export class HomeModule {}
