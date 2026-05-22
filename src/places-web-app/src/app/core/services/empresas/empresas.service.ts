import { HttpParams } from '@angular/common/http';
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

  getEmpresaById(id: number): Observable<Empresa> {
    const getByIdURL = `${id}`;
    return this.get<Empresa>(getByIdURL);
  }

  getEmpresas(): Observable<Empresa[]> {
    return this.get<Empresa[]>('');
  }

  buscarEmpresas(empresa: Empresa): Observable<any[]> {
    const params = new HttpParams()
      .set('nombre', empresa.nombre ?? '')
      .set('categoriaId', String(empresa.categoriaId) ?? '');

    const queryString = params.toString();
    return this.get<any[]>(`buscar?${queryString}`);
  }

  postEmpresas(empresa: Empresa): Observable<Empresa> {
    return this.post<Empresa>('', empresa);
  }
}