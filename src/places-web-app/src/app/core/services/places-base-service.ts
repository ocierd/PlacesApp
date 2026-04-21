import { BaseService } from "./base-service";

const placesApi = "http://localhost:8080/api";

/**
 * Clase base para servicios relacionados con "places". Extiende de BaseService y proporciona la URL base para los endpoints de "places".
 */
export class PlacesBaseService extends BaseService {

    /**
     * Constructor de la clase PlacesBaseService.
     * @param controllerName Nombre del controlador específico de "places" (por ejemplo, "auth", "users", etc.). Se utiliza para construir la URL base del servicio.
     */
    constructor(controllerName: string) {
        super(`${placesApi}/${controllerName}`);
    }

}