import { Component } from '@angular/core';
import { FormService } from '../../../core/services/form.service';
import { Form } from '../../../core/models/form.model';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { OfferService } from '../../../core/services/offer.service';
import { Offer } from '../../../core/models/offer.model';
import { DropdownModule } from 'primeng/dropdown';
import { CardModule } from 'primeng/card';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { CheckboxModule } from 'primeng/checkbox';
import { MessagesModule } from 'primeng/messages';
import { MessageService } from 'primeng/api';
import { ToastModule } from 'primeng/toast';

@Component({
  selector: 'app-form-create',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ButtonModule,
    InputTextModule,
    DropdownModule,
    CardModule,
    InputTextareaModule,
    CheckboxModule,
    MessagesModule,
    ToastModule,
  ],
  templateUrl: './form-create.component.html',
  styleUrl: './form-create.component.scss',
  providers: [MessageService],
})
export class FormCreateComponent {
  form: Form = { title: '', description: '', offerId: -1, questions: [] };
  offers: Offer[] = [];

  constructor(
    private formService: FormService,
    private offerService: OfferService,
    private messageService: MessageService
  ) {
    this.getOffers();
  }

  getOffers() {
    this.offerService.getAllOffers().subscribe((offers) => {
      this.offers = offers;
    });
  }

  addQuestion() {
    if (this.form?.questions)
      this.form.questions.push({ text: '', type: 'TEXT', generatedByIa: false });
  }

  saveForm() {
    this.formService.createForm(this.form).subscribe(
      (res) => {
        this.form = { title: '', description: '', questions: [], offerId: -1 };
        this.showSuccess();
      },
      (error) => {
        this.showError();
      }
    );
  }

  showSuccess() {
    this.messageService.add({
      severity: 'success',
      summary: 'Éxito',
      detail: 'Formulario creado correctamente',
      life: 3000,
    });
  }

  showError() {
    this.messageService.add({
      severity: 'error',
      summary: 'Error',
      detail: 'No se ha podido crear el formulario',
      life: 3000,
    });
  }
}
