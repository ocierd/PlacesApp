import { Injectable, Signal, signal, WritableSignal } from '@angular/core';
import { DEVELOPER_MENU_DATA } from '@data/menu.data';
import { environment } from '@envs/environment';
import { PlacesBaseService } from '@services/places-base-service';
import { LoggerService } from '@shared/services/logger/logger.service';
import { map, Observable } from 'rxjs';

/**
 * Servicio para gestionar el menú de navegación de la aplicación, 
 * proporcionando métodos para obtener los elementos del menú, 
 * seleccionar un elemento y mantener el estado del menú seleccionado. 
 * Utiliza señales reactivas para gestionar el estado del menú y se inyecta a nivel raíz para estar disponible en toda la aplicación.
 */
@Injectable({
  providedIn: 'root',
})
export class MenuService extends PlacesBaseService {


  private static selected: WritableSignal<MenuItem | null> = signal(null);

  private static menuItems: WritableSignal<MenuItem[]> = signal([] as MenuItem[]);

  private static menuTree: WritableSignal<MenuItem[]> = signal([] as MenuItem[]);

  private static lastRoute: string | null = null;


  constructor(private logger: LoggerService) {
    super("menu");
  }

  /**
   * Obtiene la lista de elementos del menú disponibles para el usuario autenticado, filtrados por el ID del módulo si se proporciona. Este método realiza una solicitud HTTP al endpoint '/usuario' para obtener los elementos del menú asociados al usuario autenticado, con un filtro opcional por ID de módulo. Los componentes pueden suscribirse a este observable para recibir la lista de elementos del menú y mostrarla en la interfaz de usuario, por ejemplo, en una barra de navegación o un menú lateral. Además, este método actualiza el estado interno del servicio con los elementos del menú obtenidos y verifica si hay una ruta previamente seleccionada para mantener la selección del menú consistente con la navegación del usuario.
   * @param moduloId ID del módulo para el cual se desea obtener el menú. Si se proporciona un ID de módulo, se realizará una solicitud HTTP al endpoint '/usuario' con el parámetro 'moduloId' para obtener los elementos del menú específicos para ese módulo. Si no se proporciona un ID de módulo, se realizará una solicitud al mismo endpoint sin parámetros para obtener los elementos del menú generales para el usuario.
   * @returns Un observable que emite una lista de elementos del menú disponibles para el usuario. Este método realiza una solicitud HTTP al endpoint '/usuario' para obtener los elementos del menú asociados al usuario autenticado, filtrados por el ID del módulo si se proporciona. Los componentes pueden suscribirse a este observable para recibir la lista de elementos del menú y mostrarla en la interfaz de usuario, por ejemplo, en una barra de navegación o un menú lateral. Además, este método actualiza el estado interno del servicio con los elementos del menú obtenidos y verifica si hay una ruta previamente seleccionada para mantener la selección del menú consistente con la navegación del usuario.
   */
  getMenuUsuarioPorModulo(moduloId: number | null): Observable<MenuItem[]> {
    const getMenuurl = moduloId ? `/usuario?moduloId=${moduloId}` : '/usuario';

    return this.get<MenuItem[]>(getMenuurl).pipe(
      map((menu) => {
        this.setMenuItems(menu);
        this.checkByLastRoute();
        this.logger.info('Menu items loaded:', menu);
        if (!environment.isProd) {
          return [...menu, ...DEVELOPER_MENU_DATA];
        }
        return menu;
      })
    );
  }


  private setMenuItems(menuItems: MenuItem[]): void {
    MenuService.menuItems.set(menuItems);
  }


  // getSelectedMenuItem(): Signal<MenuItem | null> {
  //   return MenuService.selected.asReadonly();
  // }

  setSelectedMenuItem(menuItem: MenuItem | null): void {
    this.setMenuTree(menuItem);
    MenuService.lastRoute = menuItem?.ruta || null;
    MenuService.selected.set(menuItem);
  }

  private checkByLastRoute(): void {
    if (MenuService.lastRoute) {
      this.setSelectedMenuItemByRoute(MenuService.lastRoute);
    }
  }

  public setSelectedMenuItemByRoute(route: string): void {
    MenuService.lastRoute = route;
    const menuItem = this.getMenuByRoute(route, MenuService.menuItems());
    this.logger.info("Menu item found for route", route, ":", menuItem);
    this.setMenuTree(menuItem);
    MenuService.selected.set(menuItem);
  }

  private getMenuByRoute(route: string, menus: MenuItem[]): MenuItem | null {
    for (let mI = 0; mI < menus.length; mI++) {
      const menu = menus[mI];
      if (menu.ruta === route) {
        return menu;
      }

      if (menu.hijos) {
        const found = this.getMenuByRoute(route, menu.hijos);
        if (found) {
          return found;
        }
      }
    }
    return null;
  }


  private setMenuTree(menu: MenuItem | null): void {
    if (!menu) {
      MenuService.menuTree.set([]);
      return;
    }
    this.logger.info("Setting menu tree for menu item:", menu.nombre);
    const menuTree = this.getInternalMenuTree(menu, MenuService.menuItems());
    this.logger.info("Menu tree set to:", menuTree);
    MenuService.menuTree.set(menuTree);
  }

  public getMenuTree(): Signal<MenuItem[]> {
    return MenuService.menuTree.asReadonly();
  }

  private getInternalMenuTree(menu: MenuItem, menus: MenuItem[]): MenuItem[] {
    const menusTree: MenuItem[] = [];
    for (let mI = 0; mI < menus.length; mI++) {
      const m = menus[mI];
      const found = m.hijos ? this.getInternalMenuTree(menu, m.hijos) : [];
      if (m.ruta === menu.ruta || found.length > 0) {
        menusTree.push(m);
        menusTree.push(...found);
        this.logger.info("Internal menu tree updated:", menusTree);
      }
    }
    return menusTree;
  }


}
