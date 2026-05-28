import { DataSource } from '@angular/cdk/table';
import { Component, Inject, signal, Signal, ViewChild, WritableSignal } from '@angular/core';
import { takeUntilDestroyed } from '@angular/core/rxjs-interop';
import { FormControl } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { EXAMPLE_SEARCH_RESULT_DATA } from '@shared/data/table.data';
import { ExampleSearchResult } from '@shared/models/ui/examples.model';
import { DEFAULT_DEBOUNCE_TIME } from '@shared/providers/places-providers';
import { LoggerService } from '@shared/services/logger/logger.service';
import {  debounceTime, startWith, tap } from 'rxjs';



/**
 * Componente que representa el formulario de búsqueda.
 */
@Component({
  selector: 'app-search-form',
  standalone: false,
  templateUrl: './search-form.component.html',
  styleUrl: './search-form.component.scss',
})
export class SearchFormComponent {

  /**
   * Columnas que se mostrarán en la tabla de resultados, definidas como un array de strings con los nombres de las columnas.
   */
  displayedColumns: string[] = ['title', 'description', 'icon', 'symbol'];

  /**
   * Datos de ejemplo para la tabla de resultados de búsqueda, que se utiliza para mostrar una lista de resultados en el componente SearchFormComponent.
   */
  datosTabla: ExampleSearchResult[] = EXAMPLE_SEARCH_RESULT_DATA;



  /**
   * Señal que contiene la lista de resultados filtrados, que se actualiza cada vez que el usuario ingresa un valor en el campo de búsqueda o filtrado del formulario 1.
   * Esta señal se utiliza para mostrar los resultados filtrados en la tabla de resultados.
   */
  filteredResults1: WritableSignal<ExampleSearchResult[]> = signal(this.datosTabla);


  /**
   * Señal que contiene la lista de resultados filtrados, que se actualiza cada vez que el usuario ingresa un valor en el campo de búsqueda o filtrado del formulario 2.
   * Esta señal se utiliza para mostrar los resultados filtrados en la tabla de resultados.
   */
  filteredResults2: WritableSignal<ExampleSearchResult[]> = signal(this.datosTabla);


  /**
   * Control del formulario 1 para el campo de búsqueda, que se utiliza para capturar el valor ingresado por el usuario y aplicar un filtro a la lista de resultados.
   */
  searchControl1: FormControl = new FormControl('');

  /**
   * Control del formulario 2 para el campo de filtrado, que se utiliza para capturar el valor ingresado por el usuario y aplicar un filtro a la lista de resultados.
   */
  searchControl2: FormControl = new FormControl('');


  constructor(
    @Inject(DEFAULT_DEBOUNCE_TIME) private debTime: number,
    private logger: LoggerService) {
    this.logger.info("Valor por defecto de debounce time: ", this.debTime);

    this.searchControl1.valueChanges.pipe(
      debounceTime(this.debTime),
      startWith(''),
      tap(value => {
        this.logger.info("Valor de búsqueda 1: ", value);
        this.filteredResults1.set(this.filterResults(value));
      }),
      takeUntilDestroyed() // Estará suscrito hasta que se destruya este componente (SearchFormComponent)
    ).subscribe();

    this.searchControl2.valueChanges.pipe(
      debounceTime(this.debTime),
      startWith(''),
      tap(value => {
        this.logger.info("Valor de búsqueda 2: ", value);
        this.filteredResults2.set(this.filterResults(value));
      }),
      takeUntilDestroyed() // Estará suscrito hasta que se destruya este componente (SearchFormComponent)
    ).subscribe();

  }

  /**
   * Filtra la lista de resultados en función del valor ingresado en el campo de búsqueda o filtrado.
   * @param value Valor ingresado en el campo de búsqueda o filtrado, que se utiliza para filtrar la lista de resultados. El filtro se aplica a las propiedades title, description y symbol de cada resultado, y se ignoran mayúsculas y minúsculas.
   * @returns Lista de resultados filtrados.
   */
  filterResults(value: string): ExampleSearchResult[] {
    const filterValue = value.toLowerCase();
    const filtered = this.datosTabla.filter(result => {
      const match = result.title.toLowerCase().includes(filterValue) ||
        result.description.toLowerCase().includes(filterValue) ||
        result.symbol.toLowerCase().includes(filterValue);
      return match;
    });
    return filtered;
  }




}
