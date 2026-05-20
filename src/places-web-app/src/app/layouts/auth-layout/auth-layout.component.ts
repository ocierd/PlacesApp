import { Component, effect, signal, Signal } from '@angular/core';
import { AuthRoutingModule } from "../../modules/auth/auth-routing.module";
import { OverlayContainer } from '@angular/cdk/overlay';


type authTheme = 'auth-light-theme' | 'auth-dark-theme';

@Component({
  selector: 'app-auth-layout',
  imports: [AuthRoutingModule],
  templateUrl: './auth-layout.component.html',
  styleUrl: './auth-layout.component.scss',
})
export class AuthLayoutComponent {

  theme: Signal<authTheme> = signal('auth-light-theme');

  constructor(private overlayContainer: OverlayContainer) {

    effect(() => {
      const currentTheme = this.theme();
      const containerElement = this.overlayContainer.getContainerElement();
      containerElement.classList.remove(...containerElement.classList.value.split(' ').filter(c => c.endsWith('-theme')));
      containerElement.classList.add(currentTheme);
    });
    
  }
}
