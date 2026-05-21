import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DeveloperRoutingModule } from './developer-routing-module';
import { SharedModule } from '@shared/shared.module';
import { ButtonsComponent } from './pages/buttons/buttons.component';
import { AutocompleteExampleComponent } from './pages/autocomplete-example/autocomplete-example.component';
import { DialogsExampleComponent } from './pages/dialogs-example/dialogs-example.component';

/**
 * Módulo para la sección de desarrollo, que incluye ejemplos de componentes y funcionalidades para desarrolladores.
 * Este módulo se carga de forma perezosa (lazy loading) cuando el usuario navega a la ruta correspondiente.
 */
@NgModule({
  declarations: [ButtonsComponent, AutocompleteExampleComponent, DialogsExampleComponent],
  imports: [CommonModule, DeveloperRoutingModule, SharedModule],
})
export class DeveloperModule {}
