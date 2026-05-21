import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { ModuloDto } from '@shared/models/modulo.model';

@Component({
  selector: 'app-modulos-dialog',
  standalone: false,
  templateUrl: './modulos-dialog.component.html',
  styleUrl: './modulos-dialog.component.scss',
})
export class ModulosDialogComponent {

  constructor(
    @Inject(MAT_DIALOG_DATA) public modulos: ModuloDto[],
    private dialogRef: MatDialogRef<ModulosDialogComponent>,) {

  }


  selectModulo(modulo: ModuloDto | null) {
    
    this.dialogRef.close(modulo);
    
  }



}

