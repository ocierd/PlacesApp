import { Component } from '@angular/core';
import { AuthRoutingModule } from "../../modules/auth/auth-routing.module";

@Component({
  selector: 'app-main-layout',
  imports: [AuthRoutingModule],
  templateUrl: './main-layout.component.html',
  styleUrl: './main-layout.component.scss',
})
export class MainLayoutComponent {}
