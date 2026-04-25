import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SpinnerComponent } from './generic/spinner/spinner.component';
import { MaterialModule } from '@shared/material/material-module';
import { DirectivesModule } from "@shared/directives/directives.module";

/**
 * Módulo para componentes compartidos
 */
@NgModule({
  declarations: [SpinnerComponent],
  imports: [CommonModule, MaterialModule, DirectivesModule],
  exports: [SpinnerComponent]
})
export class ComponentsModule { }
