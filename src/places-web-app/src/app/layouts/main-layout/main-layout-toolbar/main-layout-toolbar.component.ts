import { Component, model, ModelSignal, Signal, computed, effect, inject, DOCUMENT } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '@services/auth/auth-service';
import { MenuService } from '@services/menu/menu.service';
import { MaterialModule } from '@shared/material/material-module';
import { StorageService } from '@shared/services/storage/storage.service';
import { ThemeService } from '@shared/services/theme/theme.service';


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

  private readonly document: Document = inject(DOCUMENT);

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

  currentTheme: Signal<'places-light-theme' | 'places-dark-theme'>;

  themeIcon = computed(() => {
    return this.currentTheme() === 'places-light-theme' ? 'dark_mode' : 'light_mode';
  });


  menuTreeLabels: Signal<string> = computed(() => {
    const menuTree = this.menuService.getMenuTree();
    return menuTree().map(m => m.nombre).join(' > ');
  });

  constructor(
    private menuService: MenuService,
    private router: Router,
    private authService: AuthService,
    private storageService: StorageService,
    private themeService: ThemeService
  ) {
    this.currentTheme = this.themeService.getCurrentTheme();

    effect(() => {
      const theme = this.currentTheme();
      this.document.body.className = theme; // Cambia la clase del body para aplicar el tema
    });
  }


  changeTheme() {
    const newTheme = this.currentTheme() === 'places-light-theme' ? 'places-dark-theme' : 'places-light-theme';
    this.themeService.setTheme(newTheme);
  }


  /**
   * Limpia todas las variables del almacenamiento local y redirige a "Auth" (login)
   */
  closeSession() {
    this.authService.clearToken();
    this.storageService.clear();
    this.router.navigate(['auth']);
  }
}
