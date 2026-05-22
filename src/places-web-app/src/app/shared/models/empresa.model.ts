import { Categoria } from "./categoria.model";

export interface Empresa {
  empresaId: number;
  nombre: string;
  categoriaId: number;
  categoria: Categoria;
}