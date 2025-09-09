import { Component, inject } from '@angular/core';
import { FormBuilder, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { InputTextModule } from 'primeng/inputtext';
import { PasswordModule } from 'primeng/password';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { AuthService } from '../../../core/services/auth.service';
import { Router } from '@angular/router';
import { AuthStateService } from '../../../core/services/authState.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    CommonModule,
    ReactiveFormsModule,
    InputTextModule,
    PasswordModule,
    ButtonModule,
    DropdownModule,
    FormsModule,
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss',
})
export class RegisterComponent {
  private fb = inject(FormBuilder);
  private authService = inject(AuthService);
  private router = inject(Router);
  private authState = inject(AuthStateService);
  form = this.fb.group({
    name: ['', Validators.required],
    lastName: [''],
    city: [''],
    email: ['', [Validators.required, Validators.email]],
    password: ['', Validators.required],
    role: [''],
  });

  roleOptions = [
    { label: 'USER', value: 'USER' },
    { label: 'ADMIN', value: 'ADMIN' },
    { label: 'RRHH', value: 'RRHH' },
  ];

  isAdmin(): boolean {
    console.log('this.authState', this.authState, this.authState.hasRole('ADMIN'));
    return this.authState.hasRole('ADMIN');
  }

  onSubmit() {
    const value = this.form.value;
    const payload = {
      email: value.email ?? '',
      name: value.name ?? '',
      lastName: value.lastName ?? '',
      city: value.city ?? '',
      password: value.password ?? '',
      role: this.isAdmin() ? [value.role ?? 'USER'] : ['USER'],
    };
    this.authService.register(payload).subscribe({
      next: () => this.router.navigate(['/login']),
      error: (err) => console.error(err),
    });
  }
}
