import { CommonModule } from '@angular/common';
import { Component, Input, OnInit, signal, WritableSignal } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { CategoriasService } from '@services/categorias/categorias.service';
import { EmpresasService } from '@services/empresas/empresas.service';
import { Categoria } from '@shared/models/categoria.model';
import { Empresa } from '@shared/models/empresa.model';
import { LoggerService } from '@shared/services/logger/logger.service';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-registro',
  standalone: false,
  templateUrl: './registro.component.html',
  styleUrl: './registro.component.scss',
})
export class RegistroComponent implements OnInit {
  empresaId: number | null = null;

  /**
   * Formulario empresa
   */
  empresaForm: FormGroup;

  cargando = signal(false);

  empresa: WritableSignal<Empresa> = signal({} as Empresa);
  categorias: WritableSignal<Categoria[]> = signal([] as Categoria[]);

  get nombreControl(): AbstractControl {
    return this.getControlByName('nombre');
  }

  get categoriaControl(): AbstractControl {
    return this.getControlByName('nombre');
  }

  constructor(private fb: FormBuilder,
    private empresaService: EmpresasService,
    private categoriaService: CategoriasService,
    private logger: LoggerService,
    private route: ActivatedRoute
  ) {
    this.route.params.subscribe(p => {
      const id = Number(p["id"]);
      this.logger.info(id);
      this.empresaId = id;
    });

    this.empresaForm = this.fb.group({
      nombre: [null, [Validators.required]],
      categoriaId: [null, [Validators.required]]
    })


  }

  async ngOnInit(): Promise<void> {
    this.getCategorias();
    this.getEmpresa();
  }

  private getControlByName(name: string): AbstractControl {
    const control: AbstractControl | null = this.empresaForm.get(name);
    if (!control) {
      throw new Error(`Control '${name}' no encontrado en el formulario`);
    }
    return control;
  }

  async getCategorias(): Promise<void> {
    try {
      const lst = await firstValueFrom(this.categoriaService.getCategorias());
      this.categorias.set(lst);
    } catch (error) {
      this.logger.error('Error al cargar las categorías:', error);
    }
    finally {

    }
  }

  async getEmpresa(): Promise<void> {
    try {
      if (!this.empresaId) {
        return;
      }

      this.cargando.set(true);

      const item = await firstValueFrom(this.empresaService.getEmpresaById(this.empresaId));
      this.empresa.set(item);

      this.empresaForm.patchValue({
        nombre: this.empresa().nombre,
        categoriaId: this.empresa().categoriaId
      });

    } catch (error) {
      this.logger.error('Error al cargar los estados:', error);
    }
    finally {
      this.cargando.set(false);
      this.logger.info('Proceso de carga de estados finalizado');
    }
  }

  async guardarEmpresa(): Promise<void> {
    try {
      this.cargando.set(true);
      const empresa = this.empresaForm.getRawValue() as Empresa;

      const response = await firstValueFrom(this.empresaService.postEmpresas(empresa));
    } catch (error) {
      this.logger.error('Error al guardar la empresa:', error);
    }
    finally {
      this.cargando.set(false);
    }
  }
}