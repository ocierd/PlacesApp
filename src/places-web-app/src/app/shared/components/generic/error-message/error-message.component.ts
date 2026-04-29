import { Component, inject, input, OnDestroy, OnInit, signal } from '@angular/core';
import { AbstractControl } from '@angular/forms';
import { ValidationsService } from '@shared/services/validations/validations.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-error-message',
  standalone: false,
  templateUrl: './error-message.component.html',
  styleUrl: './error-message.component.scss',
})
export class ErrorMessageComponent implements OnInit, OnDestroy {

  /**
   * Control del que se mostrarán los mensajes de error. 
   * Se utiliza un input para recibir el control desde el componente padre. 
   * El tipo AbstractControl permite que este componente sea reutilizable con cualquier tipo de control de formulario (FormControl, FormGroup, FormArray).
   */
  control = input<AbstractControl | null>(null);

  /**
   * Mensaje de error a mostrar. Se actualiza automáticamente cuando cambian los errores del control.
   */
  message = signal<string | null>(null);

  /**
   * Suscripción a los cambios del control para actualizar el mensaje de error en tiempo real. 
   * Se almacena para poder cancelar la suscripción en ngOnDestroy y evitar fugas de memoria.
   */
  changesSubs?: Subscription


  constructor(private validationService: ValidationsService) {
  }

  /**
   * Se suscribe a los cambios del control para actualizar el mensaje de error cada vez que cambian los errores del control.
   */
  ngOnInit(): void {
    this.changesSubs = this.control()?.valueChanges
      .subscribe(value => {
        if (this.control()?.errors) {
          const errorMessage = this.validationService.getErrorMessage(this.control()!);
          this.message.set(errorMessage);
        } else {
          this.message.set(null);
        }
      });
  }

  /**
   * Cancela la suscripción a los cambios del control para evitar fugas de memoria cuando el componente se destruye.
   */
  ngOnDestroy(): void {
    if (this.changesSubs) {
      this.changesSubs.unsubscribe();
    }
  }

}
