import { Component } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-about-us',
  standalone: true,
  imports: [ButtonModule, CommonModule, CardModule],
  templateUrl: './about-us.component.html',
  styleUrl: './about-us.component.scss',
})
export class AboutUsComponent {
  infoBlocks = [
    {
      title: 'Nuestra misión',
      text: 'Crear procesos selectivos ágiles, personalizados y justos mediante formularios inteligentes.',
      icon: 'pi pi-bolt',
    },
    {
      title: 'Nuestra visión',
      text: 'Facilitar la formación y selección con herramientas intuitivas e impulsadas por IA.',
      icon: 'pi pi-eye',
    },
    { title: 'Nuestros valores', text: 'Innovación · Equidad · Eficiencia', icon: 'pi pi-heart' },
  ];

  features = [
    {
      title: 'Formularios dinámicos',
      desc: 'Las preguntas se adaptan según respuestas previas.',
      icon: 'pi pi-sliders-h',
    },
    {
      title: 'Ayuda con IA',
      desc: 'Genera esquemas de preguntas automáticamente.',
      icon: 'pi pi-cog',
    },
    {
      title: 'Procesos más rápidos',
      desc: 'Más eficiencia para administrativos y candidatos.',
      icon: 'pi pi-clock',
    },
  ];
}
