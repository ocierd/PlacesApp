import { ComponentType } from '@angular/cdk/overlay';
import { Injectable } from '@angular/core';
import { MatDialog, MatDialogConfig, MatDialogRef } from '@angular/material/dialog';

@Injectable({
  providedIn: 'root',
})
export class DialogService {


  constructor(private dialog: MatDialog) {

  }

  /**
   * Abre un diálogo modal con el componente especificado y la configuración proporcionada.
   * @param component El componente que se va a mostrar en el diálogo. Debe ser un componente Angular válido.
   * @param config Configuración opcional para el diálogo, que puede incluir datos a pasar al componente, opciones de diseño, etc. Esta configuración se basa en MatDialogConfig de Angular Material.
   * @returns Una referencia al diálogo abierto, que permite interactuar con él, como cerrarlo o suscribirse a eventos.
   */
  openDialog<T, D, R>(component: ComponentType<T>, config: MatDialogConfig<D>): MatDialogRef<T, R> {
    return this.dialog.open<T, D, R>(component, config);
  }

}
