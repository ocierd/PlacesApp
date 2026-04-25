import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DirectivesModule } from './directives/directives.module';
import { MaterialModule } from './material/material-module';
import { ComponentsModule } from './components/components-module';


const modulos = [
  CommonModule,
  FormsModule,
  ReactiveFormsModule,
  DirectivesModule,
  MaterialModule,
  ComponentsModule
];



@NgModule({
  declarations: [],
  imports: [...modulos],
  exports: [...modulos],
})
export class SharedModule { }
