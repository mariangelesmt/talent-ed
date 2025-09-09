// services/form.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class ResponseService {
  private apiUrl = 'http://localhost:8080/responses';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Response[]> {
    return this.http.get<Response[]>(this.apiUrl);
  }

  saveResponse(body: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}`, body);
  }

  getResponseByOffer(id: any) {
    return this.http.get<Response[]>(this.apiUrl + '/offer/' + id);
  }

  updateStatusResponse(id: number, status: string) {
    const body = { status: status };
    return this.http.patch<any>(`${this.apiUrl + '/' + id}`, body);
  }
}
