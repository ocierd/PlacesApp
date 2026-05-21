import { HttpClient } from "@angular/common/http";
import { inject } from "@angular/core";
import { Observable } from "rxjs";


/**
 * Clase base para servicios que interactúan con una API RESTful.
 * Proporciona métodos genéricos para realizar solicitudes HTTP GET y POST.
 * El constructor recibe la URL base del servicio, que se utiliza para construir las URLs de los endpoints.
 */
export class BaseService {

    /**
     * Cliente HTTP para realizar solicitudes. Se inyecta utilizando la función `inject` de Angular.
     */
    protected httpClient: HttpClient = inject(HttpClient);


    /**
     * Constructor de la clase BaseService.
     * @param baseUrl URL base del servicio.
     */

    constructor(private baseUrl: string) {
    }

    /**
     * Método para realizar una solicitud GET a un endpoint específico del servicio.
     * @param endpoint Endpoint específico del servicio (por ejemplo, "login", "users", etc.). Se concatena con la URL base para construir la URL completa de la solicitud.
     * @returns  Observable con la respuesta de la solicitud GET.   
     */
    protected get<T>(endpoint: string): Observable<T> {
        const url = this.getUrl(endpoint);
        return this.httpClient.get<T>(url);
    }


    /**
     * Método para realizar una solicitud POST a un endpoint específico del servicio.
     * @param endpoint Endpoint específico del servicio (por ejemplo, "login", "users", etc.). Se concatena con la URL base para construir la URL completa de la solicitud.
     * @param data Datos que se enviarán en el cuerpo de la solicitud POST.
     * @returns Observable con la respuesta de la solicitud POST.
     */
    protected post<T>(endpoint: string, data: any): Observable<T> {
        const url = this.getUrl(endpoint);
        return this.httpClient.post<T>(url, data);
    }

    /**
     * Método para construir la URL completa para un endpoint específico del servicio.
     * @param endpoint Endpoint específico del servicio (por ejemplo, "login", "users", etc.). Se concatena con la URL base para construir la URL completa de la solicitud.
     * @returns  URL completa para el endpoint específico del servicio.
     */
    private getUrl(endpoint: string): string {
        if(typeof endpoint === 'string' && endpoint.trim() !== '') {
            if(endpoint.startsWith('/')) {
                return `${this.baseUrl}${endpoint}`;
            }
            return `${this.baseUrl}/${endpoint}`;
        }
        return `${this.baseUrl}`;
    }


}