import { Component } from '@angular/core';
import { AuthRoutingModule } from "../../modules/auth/auth-routing.module";

@Component({
  selector: 'app-auth-layout',
  imports: [AuthRoutingModule],
  templateUrl: './auth-layout.component.html',
  styleUrl: './auth-layout.component.scss',
})
export class AuthLayoutComponent {}
