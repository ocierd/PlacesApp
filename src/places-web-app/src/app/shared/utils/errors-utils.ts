import { HttpErrorResponse } from "@angular/common/http";
import { ValidacionError } from "@shared/models/errors.model";

export class ErrorsUtils {

    private static readonly MENSAJE_ERROR = "Ocurrió un error";

    /**
     * Obtiene el mensaje dentro de un error. 
     * Principalmente cuando se obtiene una excepción de un error de respuesta HTTP.
     * Regresa un mensaje por defecto cuando no cumple con alguna condición.
     * @param error Error a revisar
     * @returns Mensaje de error para mostrar al usuario
     */
    static getValidacionError(error: any): string {
        if (error instanceof HttpErrorResponse && error.status == 409) {
            if ("errores" in error.error) {
                
                const validacionError = error.error as ValidacionError;
                const errores = validacionError.errores ?? [];
                const errorMsg = validacionError.error;

                if (errores.length && errorMsg) {
                    return [errorMsg, ...errores].join("\n");
                } else if (errorMsg && !errores.length) {
                    return errorMsg;
                }
                else if (errores.length && !errorMsg) {
                    return errores.join("\n");
                }
            }
        }
        return this.MENSAJE_ERROR;

    }


}