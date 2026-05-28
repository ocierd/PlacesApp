import { InjectionToken, Provider } from "@angular/core";
import { MAT_FORM_FIELD_DEFAULT_OPTIONS } from "@angular/material/form-field";
import { MAT_PAGINATOR_DEFAULT_OPTIONS, MatPaginatorIntl } from "@angular/material/paginator";
import { MatPaginatorIntlEs, PLACES_PAGINATION_DEFAULT_OPTIONS_PROVIDER } from "./places-pagionation-defaults";


/**
 * Valor de debounce por defecto para las búsquedas en la aplicación. 
 * Este valor se puede inyectar en cualquier componente o servicio que lo necesite, proporcionando una forma centralizada de configurar el comportamiento de debounce en toda la aplicación.
 */
export const DEFAULT_DEBOUNCE_TIME: InjectionToken<number> = new InjectionToken<number>('DEFAULT_DEBOUNCE_TIME');

/**
 * Nombre de la aplicación por defecto.
 * Este valor se puede inyectar en cualquier componente o servicio que lo necesite, proporcionando una forma centralizada de acceder al nombre de la aplicación en toda la aplicación.
 */
export const DEFAULT_APPLICATION_NAME: InjectionToken<string> = new InjectionToken<string>('DEFAULT_APPLICATION_NAME');



/**
 * Proveedores globales para la aplicación PlacesApp.
 * Aquí se pueden agregar proveedores que se utilizarán en toda la aplicación, como configuraciones por defecto para componentes de Angular Material o valores de configuración globales.
 * Estos proveedores se deben incluir en el array de providers del módulo raíz (AppModule) para que estén disponibles en toda la aplicación.
 */
export const PLACES_APP_PROVIDERS: Provider[] = [
    { provide: MAT_FORM_FIELD_DEFAULT_OPTIONS, useValue: { appearance: 'fill' } },
    { provide: DEFAULT_DEBOUNCE_TIME, useValue: 300 },
    { provide: DEFAULT_APPLICATION_NAME, useValue: 'PlacesApp' },
    PLACES_PAGINATION_DEFAULT_OPTIONS_PROVIDER,
    { provide: MatPaginatorIntl, useClass: MatPaginatorIntlEs }

];
