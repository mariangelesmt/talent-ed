// services/form.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { FormQuestion } from '../models/formQuestion.model';
import { Form } from '../models/form.model';

@Injectable({ providedIn: 'root' })
export class FormService {
  private apiUrl = 'http://localhost:8080/forms';

  constructor(private http: HttpClient) {}

  getForms(): Observable<FormQuestion[]> {
    return this.http.get<FormQuestion[]>(`${this.apiUrl}`);
  }

  getFormByOfferId(offerId: number): Observable<FormQuestion[]> {
    return this.http.get<FormQuestion[]>(`${this.apiUrl}/by-offer/${offerId}`);
  }

  createForm(form: Form): Observable<Form> {
    return this.http.post<Form>(this.apiUrl, form);
  }
}
