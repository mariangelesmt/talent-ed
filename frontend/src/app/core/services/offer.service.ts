import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Offer} from "../models/offer.model";


@Injectable({
  providedIn: 'root',
})
export class OfferService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/offers';

  getOfferById(id: number): Observable<Offer> {
    return this.http.get<Offer>(`${this.apiUrl}/${id}`);
  }

  getAllOffers(): Observable<Offer[]> {
    return this.http.get<Offer[]>(this.apiUrl);
  }
}
