import { Injectable } from '@angular/core';
import { environment } from "@envs/environment";

const isProd = environment.isProd;

/**
 * Servicio de log
 */
@Injectable({
  providedIn: 'root'
})
export class  LoggerService {

  consola: Console = console;

  get log() {
    if (isProd) {
      return function () { };
    }

    return this.consola.log.bind(this.consola);

  }

  get info() {
    if (isProd) {
      return function () { };
    }
    return this.consola.info.bind(this.consola);
  }

  get warn() {
    if (isProd) {
      return function () { };
    }
    return this.consola.warn.bind(this.consola);
  }

  get error() {
    if (isProd) {
      return function () { };
    }
    return this.consola.error.bind(this.consola);
  }

}
