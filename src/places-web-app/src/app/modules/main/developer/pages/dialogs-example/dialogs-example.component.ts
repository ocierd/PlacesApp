import { Component } from '@angular/core';
import { AlertType } from '@shared/models/ui/alert.model';
import { AlertsService } from '@shared/services/alerts/alerts.service';

/**
 * Componente de ejemplo para mostrar el uso de diálogos en la aplicación. 
 * Este componente proporciona métodos para abrir diferentes tipos de diálogos, como diálogos de confirmación y alertas de diferentes tipos (éxito, error, advertencia, información). El componente utiliza el servicio AlertsService para gestionar la apertura de los diálogos y mostrar mensajes relevantes a los usuarios según sus interacciones. Este componente sirve como una demostración práctica de cómo implementar y utilizar diálogos en la aplicación para mejorar la experiencia del usuario al proporcionar retroalimentación clara y opciones de interacción.
 */
@Component({
  selector: 'app-dialogs-example',
  standalone: false,
  templateUrl: './dialogs-example.component.html',
  styleUrl: './dialogs-example.component.scss',
})
export class DialogsExampleComponent {

  constructor(private alertService: AlertsService) {
  } 


  /**
   * Abre un cuadro de diálogo de confirmación y maneja la respuesta del usuario. Este método utiliza el servicio AlertsService para mostrar un cuadro de diálogo de confirmación con un mensaje específico. Después de que el usuario interactúa con el cuadro de diálogo (confirmando o cancelando la acción), el método maneja la respuesta mostrando una alerta adicional que indica si se confirmó o se canceló la acción, proporcionando retroalimentación clara al usuario sobre su interacción con el cuadro de diálogo.
   */
  async openConfirmDialog():Promise<void> {
    const result=await this.alertService
    .sendConfirmAsync('Confirma la acción');
    if(result.confirmed){
      this.alertService.sendAlert('Se confirmó la acción');
    }else{
      this.alertService.sendAlert('Se canceló la acción')
    }

  }

  /**
   * Abre un cuadro de diálogo de alerta con un mensaje y tipo específicos. Este método utiliza el servicio AlertsService para mostrar un cuadro de diálogo de alerta con un mensaje que indica el tipo de alerta seleccionado. El tipo de alerta puede ser 'success', 'error', 'warning' o 'info', lo que permite que el cuadro de diálogo muestre un diseño visualmente representativo del tipo de mensaje, mejorando la claridad y la experiencia del usuario al interactuar con las alertas.
   * @param type Tipo de alerta a mostrar. Este método abre un cuadro de diálogo de alerta utilizando el servicio AlertsService, mostrando un mensaje que indica el tipo de alerta seleccionado. El tipo de alerta puede ser 'success', 'error', 'warning' o 'info', lo que permite que el cuadro de diálogo muestre un diseño visualmente representativo del tipo de mensaje, mejorando la claridad y la experiencia del usuario al interactuar con las alertas.
   */
  openAlertDialog(type:AlertType):void{
    this.alertService.sendAlert(`Este es un mensaje de alerta de tipo ${type}`, type);
  }





}
