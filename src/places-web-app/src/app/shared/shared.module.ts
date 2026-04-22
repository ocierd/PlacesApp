import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DirectivesModule } from './directives/directives.module';


const modulos = [
  CommonModule,
  FormsModule,
  ReactiveFormsModule,
  DirectivesModule
];



@NgModule({
  declarations: [],
  imports: [...modulos],
  exports: [...modulos],
})
export class SharedModule { }
