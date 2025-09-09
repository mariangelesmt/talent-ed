import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ResponseService } from '../../../core/services/response.service';
import { TableModule } from 'primeng/table';
import { CommonModule, DatePipe } from '@angular/common';
import { TagModule } from 'primeng/tag';
import { DialogModule } from 'primeng/dialog';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';

@Component({
  selector: 'app-responses-table',
  standalone: true,
  imports: [
    TableModule,
    DatePipe,
    TagModule,
    CommonModule,
    DialogModule,
    FormsModule,
    ButtonModule,
    DropdownModule,
  ],
  templateUrl: './responses-table.component.html',
  styleUrl: './responses-table.component.scss',
})
export class ResponsesTableComponent {
  responses: Response[] = [];
  displayModal = false;
  selectedResponse: any = null;
  selectedStatus: any = null;

  statusOptions = [
    { label: 'Revisión', value: 'REVISION' },
    { label: 'Aceptado', value: 'ACEPTADO' },
    { label: 'Rechazado', value: 'RECHAZADO' },
    { label: 'Entrevista pendiente', value: 'PENDIENTE_ENTREVISTA' },
  ];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private responseService: ResponseService
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe((params) => {
      const id = params['id'];
      this.getResponses(id);
    });
  }

  private getResponses(id: any) {
    this.responseService.getResponseByOffer(id).subscribe((response: any) => {
      if (response.length > 0) {
        this.responses = response;
      }
    });
  }

  public getStatusSeverity(status: string) {
    switch (status) {
      case 'ACEPTADO':
        return 'success';
      case 'RECHAZADO':
        return 'danger';
      case 'REVISION':
        return 'warning';
      case 'PENDIENTE_ENTREVISTA':
        return 'info';
      default:
        return 'secondary';
    }
  }

  viewDetails(response: any) {
    this.selectedResponse = response;
    this.selectedStatus = this.selectedResponse.status;
    console.log('hola', this.selectedResponse, this.selectedStatus);
    this.displayModal = true;
  }

  saveStatus() {
    if (this.selectedResponse && this.selectedStatus) {
      this.selectedResponse.status = this.selectedStatus.value;
      this.responseService
        .updateStatusResponse(this.selectedResponse.id, this.selectedStatus)
        .subscribe((response: any) => {
          const index = this.responses.findIndex((r: any) => r.id === this.selectedResponse.id);
          if (index !== -1) {
            if (this.responses[index]) this.responses[index] = response;
          }
        });
      this.displayModal = false;
    }
  }
}
