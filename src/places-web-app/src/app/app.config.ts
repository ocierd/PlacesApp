import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { provideHttpClient, withFetch, withInterceptors } from '@angular/common/http';
import { jwtInterceptor } from './core/interceptors/jwt.interceptor';
import { PLACES_APP_PROVIDERS } from '@shared/providers/places-providers';

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes), provideClientHydration(withEventReplay()),
    provideHttpClient(withFetch(), withInterceptors([jwtInterceptor])),
    

     /** 
      * Agrega los proveedores de la aplicación PlacesApp a la configuración global de la aplicación, 
      * lo que permite que estén disponibles en toda la aplicación para su inyección y uso en componentes, 
      * servicios y otras partes de la aplicación. 
      */
    ...PLACES_APP_PROVIDERS
  ]
};
