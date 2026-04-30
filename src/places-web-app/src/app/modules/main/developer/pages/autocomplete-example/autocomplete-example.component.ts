import { Component, OnInit, Signal, signal, WritableSignal } from '@angular/core';
import { FormControl } from '@angular/forms';
import { PaisesService } from '@services/paises/paises.service';
import { Pais } from '@shared/models/pais.model';
import { LoggerService } from '@shared/services/logger/logger.service';
import { delay, firstValueFrom, map, startWith } from 'rxjs';
import { toSignal } from '@angular/core/rxjs-interop';


@Component({
  selector: 'app-autocomplete-example',
  standalone: false,
  templateUrl: './autocomplete-example.component.html',
  styleUrl: './autocomplete-example.component.scss',
})
export class AutocompleteExampleComponent implements OnInit {

  paises: WritableSignal<Pais[]> = signal([] as Pais[]);

  myControl = new FormControl();

  filtrados: Signal<Pais[] | undefined>;


  constructor(private paisesService: PaisesService,
    private logger: LoggerService
  ) {
    this.initPaises();

    this.filtrados= toSignal(this.myControl
    .valueChanges.pipe(
      startWith(''),
      map(value => this.filtrarPaises(value))
    ));

  }


  filtrarPaises(value: string): Pais[] {
    const filterValue = value.toLowerCase();
    if (!filterValue) {
      console.log("Entró al valor vacío");
      
      const filtrados= this.paises().filter(p => p.paisId < 10); // Devuelve todos los países si el valor de búsqueda está vacío
      console.log(filtrados);
      
      return filtrados;
    }
    return this.paises().filter(pais => pais.nombre.toLowerCase().includes(filterValue));
  }


  ngOnInit(): void {
    
  }

  async initPaises(): Promise<void> {
    try {
      const p = await firstValueFrom(this.paisesService.getPaises());
      this.logger.info('Paises cargados:', p);
      this.paises.set(p);
    } catch (error) {
      this.logger.error('Error al cargar los países:', error);
    }
    finally {
      this.logger.info('Proceso de carga de países finalizado');
    }
  }

}
