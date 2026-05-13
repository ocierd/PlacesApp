import { CommonModule } from '@angular/common';
import { Component, OnInit, signal } from '@angular/core';
import { AbstractControl, FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
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
  /**
   * Formulario login
   */
  empresaForm: FormGroup;

  /**
   * Indica si se está haciendo "login"
   */
  cargando = signal(false);

  categorias: Categoria[] = [];

  get nombreControl(): AbstractControl {
    return this.getControlByName('nombre');
  }

  get categoriaControl(): AbstractControl {
    return this.getControlByName('nombre');
  }

  constructor(private fb: FormBuilder,
    private empresaService: EmpresasService,
    private categoriaService: CategoriasService,
    private logger: LoggerService
  ) {
    this.empresaForm = this.fb.group({
      nombre: [null, [Validators.required]],
      categoriaId: [null, [Validators.required]]
    })
  }

  async ngOnInit(): Promise<void> {
    this.getCategorias();
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
      this.categorias = await firstValueFrom(this.categoriaService.getCategorias());
    } catch (error) {
      this.logger.error('Error al cargar las categorías:', error);
    }
    finally {

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