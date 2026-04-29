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

}
