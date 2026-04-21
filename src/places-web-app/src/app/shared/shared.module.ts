import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';


const modulos = [
  CommonModule,
  FormsModule,
  ReactiveFormsModule
];



@NgModule({
  declarations: [],
  imports: [...modulos],
  exports: [...modulos],
})
export class SharedModule { }
