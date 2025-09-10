import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';
import { AuthStateService } from './authState.service';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/auth';

  constructor(
    private http: HttpClient,
    private router: Router,
    private authState: AuthStateService
  ) {}

  login(email: string, password: string) {
    return this.http.post<any>(`${this.apiUrl}/login`, { email, password }).pipe(
      tap((res) => {
        const userRoles = {
          email: res.email,
          roles: res.roles,
          name: res.name,
          lastName: res.lastName,
        };
        localStorage.setItem('token', res.token);
        this.authState.setUser(userRoles);
      })
    );
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('token');
  }

  getToken(): string | null {
    if (typeof window !== 'undefined') {
      return localStorage.getItem('token');
    } else {
      return null;
    }
  }

  register(data: { email: string; password: string; role: string[] }) {
    return this.http.post(`${this.apiUrl}/register`, data);
  }
}
