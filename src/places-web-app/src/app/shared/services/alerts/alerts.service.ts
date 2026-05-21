import { Injectable } from '@angular/core';
import { DialogService } from '../dialog/dialog.service';
import { AlertDialogData, AlertDialogResult, AlertType } from '@shared/models/ui/alert.model';
import { AlertDialogComponent } from '@shared/components/dialogs/alert-dialog/alert-dialog.component';
import { firstValueFrom } from 'rxjs';
import { MatDialogRef } from '@angular/material/dialog';


/**
 * Servicio para gestionar las alertas en la aplicación, proporcionando métodos para mostrar diferentes tipos de alertas (éxito, error, advertencia, información) a los usuarios. Este servicio se puede utilizar en toda la aplicación para mostrar mensajes de alerta de manera consistente y centralizada, mejorando la experiencia del usuario al proporcionar retroalimentación clara sobre las acciones realizadas o los eventos que ocurren en la aplicación.
 */
@Injectable({
  providedIn: 'root',
})
export class AlertsService {

  constructor(private dialogService: DialogService) {

  }

  /**
   * Envia una alerta de éxito con el mensaje proporcionado. Este método muestra un cuadro de diálogo de alerta con el mensaje proporcionado y un icono que representa el éxito. El tipo de alerta se establece en 'success', lo que permite que el diálogo muestre un diseño visualmente representativo de un mensaje de éxito, mejorando la claridad y la experiencia del usuario al interactuar con las alertas.
   * @param message Mensaje a mostrar en la alerta de éxito. Este método muestra un cuadro de diálogo de alerta con el mensaje proporcionado y un icono que representa el éxito. El tipo de alerta se establece en 'success', lo que permite que el diálogo muestre un diseño visualmente representativo de un mensaje de éxito, mejorando la claridad y la experiencia del usuario al interactuar con las alertas.
   * @returns Referencia al cuadro de diálogo de alerta abierto, lo que permite realizar acciones adicionales o suscribirse a eventos del diálogo.
   */
  sendSuccessAlert(message: string): MatDialogRef<AlertDialogComponent, AlertDialogResult> {
    return this.sendAlert(message, 'success');
  }

  /**
   * Envia una alerta de advertencia con el mensaje proporcionado. Este método muestra un cuadro de diálogo de alerta con el mensaje proporcionado y un icono que representa una advertencia. El tipo de alerta se establece en 'warning', lo que permite que el diálogo muestre un diseño visualmente representativo de un mensaje de advertencia, mejorando la claridad y la experiencia del usuario al interactuar con las alertas.
   * @param message Mensaje a mostrar en la alerta de advertencia. Este método muestra un cuadro de diálogo de alerta con el mensaje proporcionado y un icono que representa una advertencia. El tipo de alerta se establece en 'warning', lo que permite que el diálogo muestre un diseño visualmente representativo de un mensaje de advertencia, mejorando la claridad y la experiencia del usuario al interactuar con las alertas.
   * @returns Referencia al cuadro de diálogo de alerta abierto, lo que permite realizar acciones adicionales o suscribirse a eventos del diálogo.
   */
  sendWarningAlert(message: string): MatDialogRef<AlertDialogComponent, AlertDialogResult> {
    return this.sendAlert(message, 'warning');
  }

  /**
   * Envia una alerta de error con el mensaje proporcionado. Este método muestra un cuadro de diálogo de alerta con el mensaje proporcionado y un icono que representa un error. El tipo de alerta se establece en 'error', lo que permite que el diálogo muestre un diseño visualmente representativo de un mensaje de error, mejorando la claridad y la experiencia del usuario al interactuar con las alertas.
   * @param message Mensaje a mostrar en la alerta de error. Este método muestra un cuadro de diálogo de alerta con el mensaje proporcionado y un icono que representa un error. El tipo de alerta se establece en 'error', lo que permite que el diálogo muestre un diseño visualmente representativo de un mensaje de error, mejorando la claridad y la experiencia del usuario al interactuar con las alertas.
   * @returns Referencia al cuadro de diálogo de alerta abierto, lo que permite realizar acciones adicionales o suscribirse a eventos del diálogo.
   */
  sendErrorAlert(message: string): MatDialogRef<AlertDialogComponent, AlertDialogResult> {
    return this.sendAlert(message, 'error');

  }

  /**
   * Envia una alerta de información con el mensaje proporcionado. Este método muestra un cuadro de diálogo de alerta con el mensaje proporcionado y un icono que representa información. El tipo de alerta se establece en 'info', lo que permite que el diálogo muestre un diseño visualmente representativo de un mensaje de información, mejorando la claridad y la experiencia del usuario al interactuar con las alertas. 
   * @param message Mensaje a mostrar en la alerta de información. Este método muestra un cuadro de diálogo de alerta con el mensaje proporcionado y un icono que representa información. El tipo de alerta se establece en 'info', lo que permite que el diálogo muestre un diseño visualmente representativo de un mensaje de información, mejorando la claridad y la experiencia del usuario al interactuar con las alertas.
   * @returns Referencia al cuadro de diálogo de alerta abierto, lo que permite realizar acciones adicionales o suscribirse a eventos del diálogo.
   */
  sendInfoAlert(message: string): MatDialogRef<AlertDialogComponent, AlertDialogResult> {
    return this.sendAlert(message, 'info');
  }

  /**
   * Envia una alerta con el mensaje y tipo proporcionados. Este método muestra un cuadro de diálogo de alerta con el mensaje proporcionado y un icono que representa el tipo de alerta. El tipo de alerta puede ser 'success', 'error', 'warning' o 'info', lo que permite que el diálogo muestre un diseño visualmente representativo del tipo de mensaje, mejorando la claridad y la experiencia del usuario al interactuar con las alertas.
   * @param message Mensaje a mostrar en la alerta.
   * @param type Tipo de alerta ('success', 'error', 'warning' o 'info'). Por defecto es 'info'.
   * @returns Referencia al cuadro de diálogo de alerta abierto, lo que permite realizar acciones adicionales o suscribirse a eventos del diálogo.
   */
  sendAlert(message: string, type: AlertType = 'info')
    : MatDialogRef<AlertDialogComponent, AlertDialogResult> {
    return this.dialogService.openDialog(AlertDialogComponent,
      {
        data: {
          message: message,
          type
        }
      }
    );
  }


  /**
   *  Envia una alerta de confirmación con el texto proporcionado. Este método muestra un cuadro de diálogo de alerta con el mensaje proporcionado y un icono que representa una advertencia, junto con botones para confirmar o cancelar la acción. El tipo de alerta se establece en 'warning', lo que permite que el diálogo muestre un diseño visualmente representativo de un mensaje de advertencia, mejorando la claridad y la experiencia del usuario al interactuar con las alertas. El método devuelve una promesa que se resuelve con el resultado de la interacción del usuario con el cuadro de diálogo, indicando si confirmaron o cancelaron la acción.
   * @param text Texto a mostrar en el cuadro de diálogo de confirmación. Este método muestra un cuadro de diálogo de alerta con el mensaje proporcionado y un icono que representa una advertencia, junto con botones para confirmar o cancelar la acción. El tipo de alerta se establece en 'warning', lo que permite que el diálogo muestre un diseño visualmente representativo de un mensaje de advertencia, mejorando la claridad y la experiencia del usuario al interactuar con las alertas. El método devuelve una promesa que se resuelve con el resultado de la interacción del usuario con el cuadro de diálogo, indicando si confirmaron o cancelaron la acción.
   * @returns Promesa que se resuelve con el resultado de la interacción del usuario con el cuadro de diálogo de confirmación.
   */
  async sendConfirmAsync(text: string): Promise<AlertDialogResult> {

    const resultObs = this.dialogService
      .openDialog(AlertDialogComponent, {
        data: {
          title: 'Confirmación',
          message: text,
          type: 'warning',
          confirmButtonText: 'Sí, confirmar',
          cancelButtonText: 'No, cancelar'
        }
      }).afterClosed();

    const result = await firstValueFrom(resultObs);
    return result as AlertDialogResult;

  }


}
