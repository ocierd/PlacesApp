import { Component, OnInit, signal, WritableSignal } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { CategoriasService } from '@services/categorias/categorias.service';
import { EmpresasService } from '@services/empresas/empresas.service';
import { Categoria } from '@shared/models/categoria.model';
import { Empresa } from '@shared/models/empresa.model';
import { LoggerService } from '@shared/services/logger/logger.service';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-catalogo',
  standalone: false,
  templateUrl: './catalogo.component.html',
  styleUrl: './catalogo.component.scss',
})
export class CatalogoComponent implements OnInit {
  busquedaForm: FormGroup;

  cargando = signal(false);

  categorias: WritableSignal<any[]> = signal([] as any[]);
  empresas: WritableSignal<Empresa[]> = signal([] as Empresa[]);

  get nombreControl(): AbstractControl {
    return this.getControlByName('nombre');
  }

  get categoriaControl(): AbstractControl {
    return this.getControlByName('nombre');
  }

  constructor(
    private fb: FormBuilder,
    private empresaService: EmpresasService,
    private categoriaService: CategoriasService,
    private logger: LoggerService,
    private router: Router) {
    this.busquedaForm = this.fb.group({
      nombre: [null],
      categoriaId: [null]
    })
  }

  async ngOnInit(): Promise<void> {
    this.getCategorias();
  }

  private getControlByName(name: string): AbstractControl {
    const control: AbstractControl | null = this.busquedaForm.get(name);
    if (!control) {
      throw new Error(`Control '${name}' no encontrado en el formulario`);
    }
    return control;
  }

  async getCategorias(): Promise<void> {
    try {
      this.cargando.set(true);
      const lst = await firstValueFrom(this.categoriaService.getCategorias());
      this.categorias.set(lst);
    } catch (error) {
      this.logger.error('Error al cargar las categorías:', error);
    }
    finally {
      this.cargando.set(false);
      this.logger.info('Proceso de carga de empresas finalizado');
    }
  }

  async getEmpresas(): Promise<void> {
    try {
      this.cargando.set(true);
      const empresa = this.busquedaForm.getRawValue() as Empresa;
      const lst = await firstValueFrom(this.empresaService.buscarEmpresas(empresa));
      this.empresas.set(lst);
    } catch (error) {
      this.logger.error('Error al cargar las empresas:', error);
    }
    finally {
      this.cargando.set(false);
      this.logger.info('Proceso de carga de empresas finalizado');
    }
  }

  buscar() {
    this.getEmpresas();
  }

  crearEmpresa(empresaId: number | null | undefined) {

  }

  editarEmpresa(itemAEditar: any) {
    this.router.navigate(['/admin/empresa/registro/' + itemAEditar.empresaId]);
  }
}