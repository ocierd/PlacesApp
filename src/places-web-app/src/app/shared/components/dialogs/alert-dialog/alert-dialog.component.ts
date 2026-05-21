import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { AlertDialogData, AlertDialogResult } from '@shared/models/ui/alert.model';

/**
 * Componente de diálogo de alerta que se utiliza para mostrar mensajes de alerta a los usuarios. 
 * Este componente recibe datos a través de la inyección de dependencias, específicamente un mensaje que se muestra en el contenido del diálogo. El diseño del diálogo incluye un título, el mensaje de alerta y acciones para cancelar o aceptar la alerta. Este componente se puede utilizar en toda la aplicación para mostrar mensajes de alerta de manera consistente y centralizada, mejorando la experiencia del usuario al proporcionar retroalimentación clara sobre las acciones realizadas o los eventos que ocurren en la aplicación.
 */
@Component({
  selector: 'app-alert-dialog',
  standalone: false,
  templateUrl: './alert-dialog.component.html',
  styleUrl: './alert-dialog.component.scss',
})
export class AlertDialogComponent {

  constructor(@Inject(MAT_DIALOG_DATA) public data: AlertDialogData,
    private dialogRef: MatDialogRef<AlertDialogComponent>) {

  }
  /**
   * Obtiene el icono correspondiente al tipo de alerta. 
   * Este método devuelve un string que representa el nombre del icono a mostrar en el diálogo de alerta, basado en el tipo de alerta especificado en los datos inyectados. Por ejemplo, si el tipo de alerta es 'success', se devuelve 'check_circle'; si es 'error', se devuelve 'error'; si es 'warning', se devuelve 'warning'; y si es 'info', se devuelve 'info'. Esto permite que el diálogo de alerta muestre un icono visualmente representativo del tipo de mensaje que se está comunicando al usuario, mejorando la claridad y la experiencia del usuario al interactuar con las alertas.
   */
  get alertIcon(): string {
    switch (this.data.type) {
      case 'success':
        return 'check_circle';
      case 'error':
        return 'error';
      case 'warning':
        return 'warning';
      case 'info':
        return 'info';
      default:
        return 'info';
    }
  }

  /**
   * Cierra el diálogo de alerta y devuelve el resultado.
   * @param confirmed Indica si el usuario confirmó la acción (true), canceló la acción (false) o no se especificó (null).
   */
  closeDialog(confirmed: boolean | null = null): void {
    const result: AlertDialogResult = { confirmed };
    this.dialogRef.close(result);

  }
}
