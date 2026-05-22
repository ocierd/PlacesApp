import { Injectable } from '@angular/core';
import { PlacesBaseService } from '@services/places-base-service';
import { Categoria } from '@shared/models/categoria.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class CategoriasService extends PlacesBaseService {

  constructor() {
    super('categorias');
  }

  getCategorias(): Observable<Categoria[]> {
    return this.get<Categoria[]>('');
  }
}