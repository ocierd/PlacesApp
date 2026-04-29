import { Component, signal, WritableSignal } from '@angular/core';
import { PaisesService } from '@services/paises/paises.service';
import { Pais } from '@shared/models/pais.model';
import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-main-home',
  standalone: false,
  templateUrl: './main-home.component.html',
  styleUrl: './main-home.component.scss',
})
export class MainHomeComponent {

  paises: WritableSignal<Pais[]> = signal<Pais[]>([]);


  constructor(private paisesService: PaisesService) {
this.initPaises();
  }


  async initPaises():Promise<void>{
    const paises = await firstValueFrom(this.paisesService.getPaises());
    this.paises.set(paises);
  }


}
