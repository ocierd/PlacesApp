import { Injectable } from '@angular/core';
import { AbstractControl, ValidationErrors } from '@angular/forms';

@Injectable({
  providedIn: 'root',
})
export class ValidationsService {


  /**
   *  Obtiene el mensaje de error correspondiente al primer error encontrado en el control.
   * @param control Control del formulario a validar
   * @returns  Mensaje de error correspondiente al primer error encontrado en el control, o null si no hay errores.
   */
  getErrorMessage(control: AbstractControl): string | null {
    const errors = control.errors;
    if (!errors) {
      return null;
    }
    for (const errorKey in errors) {
      if (errors.hasOwnProperty(errorKey)) {
        const errorMessage = this.validateControl(errorKey, control.value);
        if (errorMessage) {
          return errorMessage;
        }
      }
    }
    return null;
  }


  /**
   *  Genera un mensaje de error personalizado basado en el tipo de error y el valor del control.
   * @param key Tipo de error a validar (por ejemplo, 'required', 'email', 'password', etc.)
   * @param value Valor del control que se está validando, que puede ser útil para generar mensajes de error personalizados.
   * @returns Mensaje de error correspondiente al tipo de error, o null si no hay mensaje definido.
   */
  private validateControl(key: string, value: any): string | null {
    const errorMessages: { [key: string]: string } = {
      required: 'Este campo es obligatorio',
      email: 'Formato de correo electrónico no válido',
      password: 'La contraseña debe tener al menos 8 caracteres, una letra mayúscula, una letra minúscula y un número',
      username: 'El nombre de usuario debe tener entre 3 y 20 caracteres y solo puede contener letras, números y guiones bajos',
      unused: `Este campo no se está utilizando: ${value}`,
      minlength: 'El valor no cumple con la longitud mínima requerida',
      maxlength: 'El valor excede la longitud máxima permitida',
    };
    return errorMessages[key] || null;
  }

  /**
   *  Valida si el valor del control cumple con el formato de correo electrónico.
   * @param control Control del formulario que se va a validar como correo electrónico. El valor del control se espera que sea una cadena de texto que represente una dirección de correo electrónico.
   * @returns  Un objeto de errores de validación si el valor del control no cumple con el formato de correo electrónico, o null si el valor es válido. El objeto de errores tendrá una propiedad 'email' con el valor true para indicar que el error es de tipo email.
   */
  static validateEmail(control: AbstractControl): ValidationErrors | null {
    const email = control.value;
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email) ? null : { email: true };
  }

  /**
   *  Valida si el valor del control cumple con el formato de contraseña.
   * @param control Control del formulario que se va a validar como contraseña. El valor del control se espera que sea una cadena de texto que represente una contraseña.
   * @returns  Un objeto de errores de validación si el valor del control no cumple con el formato de contraseña, o null si el valor es válido. El objeto de errores tendrá una propiedad 'password' con el valor true para indicar que el error es de tipo password.
   */
  static validatePassword(control: AbstractControl): ValidationErrors | null {
    const password = control.value;
    // La contraseña debe tener al menos 8 caracteres, una letra mayúscula, una letra minúscula y un número
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/;
    return passwordRegex.test(password) ? null : { password: true };
  }

  /**
   * Valida si el valor del control cumple con el formato de nombre de usuario.
   * @param control Control del formulario que se va a validar como nombre de usuario. El valor del control se espera que sea una cadena de texto que represente un nombre de usuario.
   * @returns Un objeto de errores de validación si el valor del control no cumple con el formato de nombre de usuario, o null si el valor es válido. El objeto de errores tendrá una propiedad 'username' con el valor true para indicar que el error es de tipo username.
   */
  static validateUsername(control: AbstractControl): ValidationErrors | null {
    const username = control.value;
    // El nombre de usuario debe tener entre 3 y 20 caracteres y solo puede contener letras, números y guiones bajos
    const usernameRegex = /^[a-zA-Z0-9_]{3,20}$/;
    return usernameRegex.test(username) ? null : { username: true };
  }


}
