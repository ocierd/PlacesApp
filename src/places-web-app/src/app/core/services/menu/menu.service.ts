import { Injectable, Signal, signal, WritableSignal } from '@angular/core';
import { MENU_DATA } from '@data/menu.data';
import { LoggerService } from '@shared/services/logger/logger.service';
import { delay, Observable, of, tap } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class MenuService {


  private static selected: WritableSignal<MenuItem | null> = signal(null);

  private static menuItems: WritableSignal<MenuItem[]> = signal([] as MenuItem[]);

  private static menuTree: WritableSignal<MenuItem[]> = signal([] as MenuItem[]);

  private static lastRoute: string | null = null;


  constructor(private logger: LoggerService) { 
  }


  getMenuItems(): Observable<MenuItem[]> {
    return of(MENU_DATA).pipe(
      delay(200),
      tap((menu) => {
        this.setMenuItems(menu);
        this.checkByLastRoute();
        this.logger.info('Menu items loaded:', menu);
      })
    );
  }


  private setMenuItems(menuItems: MenuItem[]): void {
    MenuService.menuItems.set(menuItems);
  }


  getSelectedMenuItem(): Signal<MenuItem | null> {
    return MenuService.selected.asReadonly();
  }

  setSelectedMenuItem(menuItem: MenuItem | null): void {
    this.setMenuTree(menuItem);
    MenuService.lastRoute = menuItem?.route || null;
    MenuService.selected.set(menuItem);
  }

  private checkByLastRoute(): void {
    if (MenuService.lastRoute) {
      this.setSelectedMenuItemByRoute(MenuService.lastRoute);
    }
  }

  setSelectedMenuItemByRoute(route: string): void {
    MenuService.lastRoute = route;
    const menuItem = this.getMenuByRoute(route, MenuService.menuItems());
    this.logger.info("Menu item found for route", route, ":", menuItem);
    this.setMenuTree(menuItem);
    MenuService.selected.set(menuItem);
  }

  private getMenuByRoute(route: string, menus: MenuItem[]): MenuItem | null {
    for (let mI  = 0; mI < menus.length; mI++) {
      const menu = menus[mI];
      if (menu.route === route) {
        return menu;
      }

      if (menu.children) {
        const found = this.getMenuByRoute(route, menu.children);
        if (found) {
          return found;
        }
      }
    }
    return null;
  }


  private setMenuTree(menu:MenuItem | null): void {
    if (!menu) {
      MenuService.menuTree.set([]);
      return;
    }
    this.logger.info("Setting menu tree for menu item:", menu.label);
    const menuTree = this.getInternalMenuTree(menu, MenuService.menuItems());
    this.logger.info("Menu tree set to:", menuTree);
    MenuService.menuTree.set(menuTree);
  }

  public getMenuTree(): Signal<MenuItem[]> {
    return MenuService.menuTree.asReadonly();
  }

  private getInternalMenuTree(menu: MenuItem, menus: MenuItem[]): MenuItem[] {
    const menusTree: MenuItem[] = [];
    for (let mI  = 0; mI < menus.length; mI++) {
      const m = menus[mI];
      const found = m.children? this.getInternalMenuTree(menu, m.children) : [];
      if (m.route === menu.route || found.length > 0) {
        menusTree.push(m);
        menusTree.push(...found);
        this.logger.info("Internal menu tree updated:", menusTree);
      }
    }
    return menusTree;
  }
  

}
