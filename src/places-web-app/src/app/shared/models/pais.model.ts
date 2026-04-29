/**
 * Interfaz que representa un país, con sus propiedades y tipos de datos correspondientes.
 */
export interface Pais {
    paisId: number;
    nombre: string;
    nacionalidad?: string;
    codigo: string;
    iso2: string;
    iso3?: string;
}