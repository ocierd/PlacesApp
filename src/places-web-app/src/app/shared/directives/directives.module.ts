import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HighlightDirective } from './highlight/highlight';
import { UpperDirective } from './upper/upper.directive';

const directives=[
  HighlightDirective,
  UpperDirective
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
