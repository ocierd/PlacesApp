import { Component, input, InputSignal, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { MenuService } from '@services/menu/menu.service';
import { MaterialModule } from '@shared/material/material-module';
import { LoggerService } from '@shared/services/logger/logger.service';

/**
 * Componente del menú lateral del layout principal, que muestra los elementos de navegación definidos en el componente principal.
 * Este componente recibe el menú de navegación a través de una señal de entrada, con una estructura definida por la interfaz MenuItem.
 * El menú se muestra utilizando Angular Material y permite la navegación a través de las rutas definidas en cada elemento del menú. 
 */
@Component({
  selector: 'app-main-layout-menu',
  templateUrl: './main-layout-menu.component.html',
  styleUrl: './main-layout-menu.component.scss',
  imports: [MaterialModule, RouterLink],
})
export class MainLayoutMenuComponent {


  /**
   * Señal de entrada que recibe el menú de navegación desde el componente principal del layout, con una estructura definida por la interfaz MenuItem.
   */
  menus: InputSignal<MenuItem[]> = input([] as MenuItem[]);


  readonly panelOpenState = signal(false);

  constructor(
    private menuService: MenuService,
    private logger: LoggerService) {

  }


  setSelectedMenuItem(menuItem: MenuItem | null): void {
    this.menuService.setSelectedMenuItem(menuItem);
    this.logger.log('Menu item selected:', menuItem);
  }


}
