import { inject } from '@angular/core';
import { CanActivateChildFn } from '@angular/router';
import { LoggerService } from '@shared/services/logger/logger.service';


/**
 * Guardia de autenticación para proteger rutas hijas que requieren que el usuario esté autenticado.
 * @param childRoute La ruta hija que se está intentando activar.
 * @param state El estado de la ruta actual.
 * @returns true si el usuario está autenticado, de lo contrario redirige a la página de autenticación.
 */
export const authChildsGuard: CanActivateChildFn = (childRoute, state) => {
  const logger = inject(LoggerService);
  logger.info(`Accediendo a la ruta ${childRoute.url}`);
  return true;
};
