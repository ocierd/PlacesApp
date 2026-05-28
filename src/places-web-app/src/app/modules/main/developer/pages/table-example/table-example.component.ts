import { AfterViewInit, Component, Inject, ViewChild } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { FormControl } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { EXAMPLE_SEARCH_RESULT_DATA } from '@shared/data/table.data';
import { ExampleSearchResult } from '@shared/models/ui/examples.model';
import { DEFAULT_DEBOUNCE_TIME } from '@shared/providers/places-providers';
import { debounceTime, startWith, tap } from 'rxjs';


/**
 * Componente que representa un ejemplo de tabla con búsqueda y paginación utilizando Angular Material.
 * Este componente muestra dos tablas con datos de ejemplo, cada una con su propio campo de búsqueda y paginación.
 */
@Component({
  selector: 'app-table-example',
  standalone: false,
  templateUrl: './table-example.component.html',
  styleUrl: './table-example.component.scss',
})
export class TableExampleComponent implements AfterViewInit {
  /**
   * Columnas que se mostrarán en la tabla de resultados, definidas como un array de strings con los nombres de las columnas.
   */
  displayedColumns: string[] = ['title', 'description', 'icon', 'symbol'];


  /**
   * Control del formulario 1 para el campo de búsqueda, que se utiliza para capturar el valor ingresado por el usuario y aplicar un filtro a la lista de resultados.
   */
  searchControl1: FormControl = new FormControl('');

  /**
   * Control del formulario 2 para el campo de filtrado, que se utiliza para capturar el valor ingresado por el usuario y aplicar un filtro a la lista de resultados.
   */
  searchControl2: FormControl = new FormControl('');


  /**
   * Datos de ejemplo para la tabla de resultados de búsqueda, que se utiliza para mostrar una lista de resultados en el componente SearchFormComponent.
   */
  datosTabla: ExampleSearchResult[] = EXAMPLE_SEARCH_RESULT_DATA;


  /**
   * DataSource 1 para la tabla de resultados, que se utiliza para mostrar los datos en la tabla y aplicar filtros y paginación.
   
   */
  dataSource1 = new MatTableDataSource(this.datosTabla);

  /**
   * DataSource 2 para la tabla de resultados, que se utiliza para mostrar los datos en la tabla y aplicar filtros y paginación.
   */
  dataSource2 = new MatTableDataSource(this.datosTabla);

  /**
   * Referencia al paginador de la tabla 1, que se utiliza para controlar la paginación de los resultados en la tabla.
   */
  @ViewChild("paginator1") paginator1!: MatPaginator;


  /**
   * Referencia al paginador de la tabla 2, que se utiliza para controlar la paginación de los resultados en la tabla.
   */
  @ViewChild("paginator2") paginator2!: MatPaginator;


  constructor(@Inject(DEFAULT_DEBOUNCE_TIME) private debTime: number) {
    this.searchControl1.valueChanges.pipe(
      startWith(''),
      debounceTime(this.debTime),
      tap(value => {
        this.dataSource1.filter = value.trim().toLowerCase();
      }),
      takeUntilDestroyed()
    ).subscribe();

    this.searchControl2.valueChanges.pipe(
      startWith(''),
      debounceTime(this.debTime),
      tap(value => {
        this.dataSource2.filter = value.trim().toLowerCase();
      }),
      takeUntilDestroyed()
    ).subscribe();
  }

  /**
   * Después de que la vista se haya inicializado, se asignan los paginadores 
   * a los DataSources de las tablas para habilitar la paginación de los resultados. 
   * Esta función se ejecuta automáticamente después de que Angular haya renderizado 
   * la vista del componente.
    
   */
  ngAfterViewInit(): void {
    this.dataSource1.paginator = this.paginator1;
    this.dataSource2.paginator = this.paginator2;
  }

}

