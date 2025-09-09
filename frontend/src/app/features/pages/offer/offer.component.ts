import { Component, inject } from '@angular/core';
import { CardModule } from 'primeng/card';
import { PanelModule } from 'primeng/panel';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { InputTextModule } from 'primeng/inputtext';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { OfferService } from '../../../core/services/offer.service';
import { TagModule } from 'primeng/tag';
import { Center } from '../../../core/models/center.model';
import { CenterService } from '../../../core/services/center.service';
import { AuthStateService } from '../../../core/services/authState.service';
import { Offer } from '../../../core/models/offer.model';

@Component({
  selector: 'app-offer',
  standalone: true,
  imports: [
    // PrimeNG
    CardModule,
    PanelModule,
    ButtonModule,
    DropdownModule,
    InputTextModule,
    // Angular
    CommonModule,
    FormsModule,
    TagModule,
  ],
  templateUrl: './offer.component.html',
  styleUrl: './offer.component.scss',
})
export class OfferComponent {
  private offerService = inject(OfferService);
  private centerService = inject(CenterService);
  public ofertas: any[] = [];
  public ofertasFiltradas: any[] = [];
  public centers: Center[] = [];
  public title?: string;
  public selectedSchedule?: string;
  public selectedCenter?: Center;
  schedules = [
    { label: 'Jornada completa', value: 'FULL_TIME' },
    { label: 'Media jornada', value: 'PART_TIME' },
    { label: '1/3 jornada', value: 'ONE_THIRD_TIME' },
    { label: 'Todos', value: '' },
  ];

  constructor(
    private router: Router,
    private authState: AuthStateService
  ) {
    this.getOffers();
    this.getCenters();
  }

  getOffers() {
    this.offerService.getAllOffers().subscribe((response) => {
      this.ofertas = response;
      this.ofertasFiltradas = [...this.ofertas];
    });
  }

  getCenters() {
    this.centerService.getAllCenters().subscribe((response) => {
      this.centers = response;
    });
  }

  filtrarOfertas() {
    this.ofertasFiltradas = this.ofertas.filter((o) => {
      return (
        (!this.selectedSchedule || o.scedule === this.selectedSchedule) &&
        (!this.selectedCenter || o.center?.id === this.selectedCenter.id)
      );
    });
  }

  verDetalles(oferta: any) {
    this.router.navigate(['/offer', oferta.id]);
  }

  verInscritos(oferta: any) {
    this.router.navigate(['/responses', oferta.id]);
  }

  public getSchedule(type: string) {
    switch (type) {
      case 'FULL_TIME':
        return 'Jornada completa';
      case 'PART_TIME':
        return 'Media jornada';
      case 'ONE_THIRD_TIME':
        return '1/3 jornada';
      default:
        return 'Jornada completa';
    }
  }

  public hasRoleAdmin() {
    return this.authState.hasRole('ADMIN');
  }

  public goToForm(offer: Offer) {
    this.router.navigate(['/formulario', offer?.id]);
  }
}
