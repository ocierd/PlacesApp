import { Component, model, ModelSignal, Signal, computed } from '@angular/core';
import { MenuService } from '@services/menu/menu.service';
import { MaterialModule } from '@shared/material/material-module';


/*
  * Componente de la barra de herramientas del layout principal, que incluye un botón para abrir/cerrar el menú lateral.
  * Este componente recibe el estado de apertura del menú lateral a través de una señal de entrada y emite eventos de cambio de estado a través de una señal de salida.
  * El botón de menú alterna el estado de apertura del menú lateral al hacer clic en él.
  * El componente utiliza Angular Material para la barra de herramientas y el botón.
*/
@Component({
  selector: 'app-main-layout-toolbar',
  templateUrl: './main-layout-toolbar.component.html',
  styleUrl: './main-layout-toolbar.component.scss',
  imports: [MaterialModule],
  standalone: true,
})
export class MainLayoutToolbarComponent {

  /**
   * Señal de entrada que indica si el menú lateral está abierto o cerrado
   */
  opened: ModelSignal<boolean> = model.required<boolean>();

  /**
   * Señal de salida que emite el nuevo estado de apertura del menú lateral cuando se hace clic en el botón de menú
   */
  onClickMenu() {
    this.opened.set(!this.opened());
  }

  menuTreeLabels: Signal<string> = computed(()=>{
    const menuTree = this.menuService.getMenuTree();
    return menuTree().map(m => m.label).join(' > ');
  });

  constructor(private menuService:MenuService) {
  }
}
