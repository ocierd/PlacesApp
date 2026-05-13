import { Injectable } from '@angular/core';
import { PlacesBaseService } from '@services/places-base-service';
import { Empresa } from '@shared/models/empresa.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class EmpresasService extends PlacesBaseService {

  constructor() {
    super('empresas');
  }

  getEmpresas(): Observable<Empresa[]> {
    return this.get<Empresa[]>('');
  }

  postEmpresas(empresa: Empresa): Observable<Empresa> {
    return this.post<Empresa>('', empresa);
  }
}