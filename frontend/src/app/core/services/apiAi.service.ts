import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ApiAiService {
  private apiUrl = 'http://localhost:8080/api/ai';

  constructor(private http: HttpClient) {}

  suggestQuestion(body: any) {
    return this.http.post<any>(`${this.apiUrl}/suggest-question`, body);
  }
}
