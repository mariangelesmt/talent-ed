import { Component } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { CommonModule } from '@angular/common';
import { CardModule } from 'primeng/card';
import { CenterService } from '../../../core/services/center.service';

@Component({
  selector: 'app-centers',
  standalone: true,
  imports: [CommonModule, CardModule, ButtonModule],
  templateUrl: './centers.component.html',
  styleUrl: './centers.component.scss',
})
export class CentersComponent {
  centros: any[] = [];

  constructor(private centerService: CenterService) {
    this.obtenerCentros();
  }

  obtenerCentros() {
    this.centerService.getAllCenters().subscribe((response) => {
      this.centros = response;
    });
  }
}
