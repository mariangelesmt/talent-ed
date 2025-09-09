import { Component } from '@angular/core';
import { MenubarModule } from 'primeng/menubar';
import { ButtonModule } from 'primeng/button';
import { MenuItem } from 'primeng/api';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [CommonModule, MenubarModule, ButtonModule, RouterModule],
  templateUrl: './menu.component.html',
})
export class MenuComponent {
  items: MenuItem[] = [];

  constructor(private router: Router) {}

  ngOnInit() {
    this.items = [
      //{ label: 'Inicio', icon: 'pi pi-home', routerLink: '/' },
      { label: 'Ofertas', icon: 'pi pi-briefcase', routerLink: '/offer' },
      { label: 'Centros', icon: 'pi pi-building-columns', routerLink: '/centers' },
      { label: 'Nosotros', icon: 'pi pi-users', routerLink: '/about-us' },
      { label: 'Contacto', icon: 'pi pi-envelope', routerLink: '/contact' },
    ];
  }

  goToProfile() {
    this.router.navigate(['/profile']);
  }
}
