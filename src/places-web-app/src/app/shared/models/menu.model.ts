/**
 * Interfaz que representa un elemento del menú de navegación, con propiedades para la etiqueta, el icono, la ruta y posibles submenús.
 */
interface MenuItem {
  menuId: number;
  nombre: string;
  icono: string;
  ruta?: string;
  padreMenuId?: number;
  hijos?: MenuItem[];
}

