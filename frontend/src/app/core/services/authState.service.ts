import { Injectable, signal } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class AuthStateService {
  user = signal<{ email: string; roles: string[] } | null>(null);

  setUser(userData: { email: string; roles: string[] }) {
    console.log('userDaa', userData);
    this.user.set(userData);
  }

  hasRole(role: string): boolean {
    const u = this.user();
    return u?.roles.includes(role) ?? false;
  }

  getUserData() {
    return this.user();
  }
}
