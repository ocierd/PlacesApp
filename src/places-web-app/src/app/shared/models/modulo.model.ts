/**
 * Interfaz que define la estructura de un módulo en la aplicación. 
 * Un módulo representa una sección o funcionalidad específica dentro de la aplicación, y esta interfaz incluye propiedades como el identificador del módulo, su nombre, descripción y una ruta opcional para acceder a él. Esta interfaz se utiliza para tipar los datos relacionados con los módulos en la aplicación, facilitando la gestión y manipulación de estos datos en los servicios y componentes que los utilizan.
 */
export interface ModuloDto {
    moduloId: number;
    nombre: string;
    descripcion: string;
    ruta?: string;
}