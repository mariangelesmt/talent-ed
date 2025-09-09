import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import { TagModule } from 'primeng/tag';
import { Router } from '@angular/router';
import { DividerModule } from 'primeng/divider';
import { AuthStateService } from '../../../core/services/authState.service';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [CommonModule, CardModule, ButtonModule, TagModule, DividerModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss',
})
export class ProfileComponent {
  user: any | null = null;

  constructor(
    private router: Router,
    private authState: AuthStateService
  ) {
    this.user = this.authState.getUserData();
  }

  isAdmin(): boolean {
    return this.authState.hasRole('ADMIN');
  }

  createUser() {
    this.router.navigate(['/register']);
  }

  createForm() {
    this.router.navigate(['/form-create']);
  }

  goToOffers() {
    this.router.navigate(['/offer']);
  }

  navigateTo(url: string) {
    this.router.navigate([url]);
  }
}
