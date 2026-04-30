import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '@services/auth/auth-service';
import { LoggerService } from '@shared/services/logger/logger.service';

/**
 * Guardia de autenticación para proteger rutas que requieren que el usuario esté autenticado. 
 * Verifica si el usuario ha iniciado sesión utilizando el AuthService. 
 * Si el usuario está autenticado, permite el acceso a la ruta. Si no, redirige al usuario a la página de autenticación y registra un mensaje de advertencia en el LoggerService.
 */
export const authGuard: CanActivateFn = (route, state) => {
  const authService = inject(AuthService);
  const router = inject(Router)
  const logger = inject(LoggerService);
  if (authService.isLoggedIn()) {
    logger.info(`Se concede el paso a la ruta "${route.url}"`);
    return true;
  }
  logger.warn('El usuario intenta acceder a una ruta no autorizada');
  return router.navigate(['auth']);
};
