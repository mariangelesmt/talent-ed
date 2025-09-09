import { Component } from '@angular/core';
import { CardModule } from 'primeng/card';
import { CommonModule } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { ActivatedRoute, Router } from '@angular/router';
import { OfferService } from '../../../core/services/offer.service';
import { Offer } from '../../../core/models/offer.model';
import { TagModule } from 'primeng/tag';

@Component({
  selector: 'app-offer-detail',
  standalone: true,
  imports: [CommonModule, CardModule, ButtonModule, TagModule],
  templateUrl: './offer-detail.component.html',
  styleUrl: './offer-detail.component.scss',
})
export class OfferDetailComponent {
  public offer: Offer | null = null;

  constructor(
    private route: ActivatedRoute,
    private offerService: OfferService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      const id = params['id'];
      this.offerService.getOfferById(id).subscribe((offer) => {
        this.offer = offer;
      });
    });
  }

  public goToForm() {
    this.router.navigate(['/formulario', this.offer?.id]);
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
}
