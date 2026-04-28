import { Component, computed, model, ModelSignal, OnInit, Signal, signal, WritableSignal } from '@angular/core';
import { AuthRoutingModule } from "../../modules/auth/auth-routing.module";
import { MaterialModule } from '@shared/material/material-module';
import { MainLayoutToolbarComponent } from "./main-layout-toolbar/main-layout-toolbar.component";
import { MainLayoutMenuComponent } from './main-layout-menu/main-layout-menu.component';
import { MenuService } from '@services/menu/menu.service';
import { firstValueFrom } from 'rxjs';
import { LoggerService } from '@shared/services/logger/logger.service';
import { ComponentsModule } from '@shared/components/components-module';
import { NavigationEnd, Router } from '@angular/router';



/**
 * Componente principal del layout de la aplicación, que incluye la barra de herramientas y el menú lateral.
 * Este componente se encarga de gestionar el estado del menú lateral (abierto/cerrado) y el modo de visualización del mismo.
 */
@Component({
  selector: 'app-main-layout',
  imports: [AuthRoutingModule, MaterialModule, MainLayoutToolbarComponent, 
    MainLayoutMenuComponent, ComponentsModule],
  templateUrl: './main-layout.component.html',
  styleUrl: './main-layout.component.scss',
})
export class MainLayoutComponent implements OnInit {

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


  /**
   * Lista de elementos del menú lateral.
   * Se inicializa como vacío para luego ser actualizado con  los dtos obtenidos desde el servicio de menú.
   */
  readonly menu: WritableSignal<MenuItem[]> = signal([] as MenuItem[]);


  /**
   * Indica si el menú lateral se está cargando.
   */
  readonly isLoadingMenu: WritableSignal<boolean> = signal(false);


  /**
   * Árbol de elementos del menú lateral, obtenido desde el servicio de menú. 
   * Se utiliza para mostrar la estructura jerárquica del menú en el componente de menú lateral.
   */
  readonly menuTree: Signal<MenuItem[]>;

  /**
   * Cadena de texto que representa la ruta completa del menú seleccionado, construida a partir de las etiquetas de los elementos del menú en el árbol de menú. 
   * Se utiliza para mostrar la ruta completa del menú seleccionado en la interfaz de usuario.
   */
  readonly menuTreeLabels: Signal<string> = computed(() => this.menuTree().map(m => m.label).join(' > '));

  constructor(
    private menuService: MenuService,
    private logger: LoggerService,
    private router: Router
  ) {

    this.menuTree = this.menuService.getMenuTree();

    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        const route = event.urlAfterRedirects;
        this.menuService.setSelectedMenuItemByRoute(route);
        this.logger.info('Navigation ended:', route);
        // this.updateMenu();
      }
    });
  }


  /**
   * Inicializa el componente cargando los elementos del menú lateral desde el servicio de menú.
   */
  async ngOnInit(): Promise<void> {
    this.updateMenu();
  }

  /**
   * Actualiza el menú lateral con los elementos de navegación definidos en el modelo de menú.
   * En este ejemplo, se definen tres elementos de menú: "Home", "About" y "Contact", cada uno con su icono y ruta correspondiente.
   */
  private async updateMenu(): Promise<void> {
    try {
      this.isLoadingMenu.set(true);
      const menuItems = await firstValueFrom(this.menuService.getMenuItems());
      this.menu.set(menuItems);
    } catch (error) {
      this.logger.error('Error al cargar el menú:', error);
    }
    finally {
      this.isLoadingMenu.set(false);
    }
  }

}
