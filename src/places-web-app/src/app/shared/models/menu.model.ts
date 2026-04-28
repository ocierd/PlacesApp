/**
 * Interfaz que representa un elemento del menú de navegación, con propiedades para la etiqueta, el icono, la ruta y posibles submenús.
 */
interface MenuItem {
  label: string;
  icon: string;
  route?: string;
  children?: MenuItem[];
}

