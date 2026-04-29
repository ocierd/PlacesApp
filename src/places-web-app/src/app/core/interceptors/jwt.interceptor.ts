import { HttpInterceptorFn } from '@angular/common/http';
import { inject } from '@angular/core';
import { AuthService } from '@services/auth/auth-service';

/**
 * Interceptor para añadir el token de autenticación a las peticiones HTTP
 * Este interceptor se encarga de interceptar las peticiones HTTP salientes y añadir el token de autenticación en el encabezado Authorization si el token está disponible.
 * El token se obtiene a través del servicio de autenticación (AuthService) y se añade al encabezado de la petición utilizando el formato "Bearer <token>".
 * Si no hay un token disponible, la petición se envía sin modificar.
 * Este interceptor se puede registrar en el módulo principal de la aplicación para que se aplique a todas las peticiones HTTP realizadas desde la aplicación.
 */
export const jwtInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  const tokenData = authService.getToken();

  if (tokenData) {
    console.log("Añadiendo token a la petición:", tokenData.token);
    const newReq = req.clone({
      headers: req.headers.set('Authorization', `Bearer ${tokenData.token}`)
    })
    return next(newReq);
  }
  else {
    console.warn("No se encontró token de autenticación, enviando petición sin token");
  }
  return next(req);
};
