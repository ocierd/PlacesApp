import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SpinnerComponent } from './generic/spinner/spinner.component';
import { MaterialModule } from '@shared/material/material-module';
import { DirectivesModule } from '@shared/directives/directives.module';
import { ErrorMessageComponent } from './generic/error-message/error-message.component';


/**
 * Lista de componentes compartidos
 */
const components = [SpinnerComponent, ErrorMessageComponent];

/**
 * Módulo para componentes compartidos
 */
@NgModule({
  declarations: [...components],
  imports: [CommonModule, MaterialModule, DirectivesModule],
  exports: [...components],
})
export class ComponentsModule {}
