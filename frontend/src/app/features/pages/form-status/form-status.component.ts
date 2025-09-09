import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { MessagesModule } from 'primeng/messages';
import { CardModule } from 'primeng/card';
import { MessageModule } from 'primeng/message';
import { CommonModule } from '@angular/common';
import { Message } from 'primeng/api';

@Component({
  selector: 'app-form-status',
  standalone: true,
  imports: [CommonModule, CardModule, MessagesModule, MessageModule, ButtonModule],
  templateUrl: './form-status.component.html',
  styleUrl: './form-status.component.scss',
})
export class FormStatusComponent {
  status: 'success' | 'error' | null = null;
  messages: Message[] = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const status = this.route.snapshot.queryParamMap.get('status');

    if (status === 'success') {
      this.messages = [
        { severity: 'success', summary: '', detail: 'Formulario enviado correctamente' },
      ];
    } else if (status === 'error') {
      this.messages = [
        { severity: 'error', summary: '', detail: 'No se ha podido enviar el formulario' },
      ];
    }
  }

  goHome(): void {
    this.router.navigate(['/offer']);
  }
}
