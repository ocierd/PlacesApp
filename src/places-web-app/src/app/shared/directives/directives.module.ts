import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HighlightDirective } from './highlight/highlight';

const directives=[
  HighlightDirective
];


/**
 * Módulo que agrupa todas las directivas personalizadas de la aplicación.
 */
@NgModule({
  declarations: [...directives],
  imports: [CommonModule],
  exports: [...directives]
})
export class DirectivesModule {}
