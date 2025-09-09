import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Center } from '../models/center.model';

@Injectable({
  providedIn: 'root',
})
export class CenterService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/centers';

  getCenterById(id: number): Observable<Center> {
    return this.http.get<Center>(`${this.apiUrl}/${id}`);
  }

  getAllCenters(): Observable<Center[]> {
    return this.http.get<Center[]>(this.apiUrl);
  }
}
