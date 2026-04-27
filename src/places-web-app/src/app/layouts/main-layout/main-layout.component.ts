import { Component, model, ModelSignal, signal, WritableSignal } from '@angular/core';
import { AuthRoutingModule } from "../../modules/auth/auth-routing.module";
import { MaterialModule } from '@shared/material/material-module';
import { MainLayoutToolbarComponent } from "./main-layout-toolbar/main-layout-toolbar.component";



/**
 * Componente principal del layout de la aplicación, que incluye la barra de herramientas y el menú lateral.
 * Este componente se encarga de gestionar el estado del menú lateral (abierto/cerrado) y el modo de visualización del mismo.
 */
@Component({
  selector: 'app-main-layout',
  imports: [AuthRoutingModule, MaterialModule, MainLayoutToolbarComponent],
  templateUrl: './main-layout.component.html',
  styleUrl: './main-layout.component.scss',
})
export class MainLayoutComponent {
  /**
   * Indica si el menú lateral está abierto o cerrado
   */
  hasBackdrop: WritableSignal<boolean> = signal(true);

  /**
   * Modo de visualización del menú lateral (over, push, side)
   */
  readonly mode: WritableSignal<'over' | 'push' | 'side'> = signal('over');

  /**
   * Estado de apertura del menú lateral
   */
  readonly opened: ModelSignal<boolean> = model(false);

}
