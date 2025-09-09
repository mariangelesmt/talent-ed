import { Routes } from '@angular/router';
import { ShellComponent } from './layout/shell.component';
import { OfferComponent } from './features/pages/offer/offer.component';
import { ContactComponent } from './features/pages/contact/contact.component';
import { OfferDetailComponent } from './features/pages/offer-detail/offer-detail.component';
import { RegisterComponent } from './features/pages/register/register.component';
import { FormStepperComponent } from './features/pages/form-stepper/form-stepper.component';
import { FormStatusComponent } from './features/pages/form-status/form-status.component';
import { FormCreateComponent } from './features/pages/form-create/form-create.component';
import { ResponsesTableComponent } from './features/pages/responses-table/responses-table.component';
import { AboutUsComponent } from './features/pages/about-us/about-us.component';
import { CentersComponent } from './features/pages/centers/centers.component';
import { NotFoundComponent } from './features/pages/not-found/not-found.component';
import { LoginComponent } from './features/login/login.component';
import { ProfileComponent } from './features/pages/profile/profile.component';

export const routes: Routes = [
  {
    path: '',
    component: ShellComponent,
    children: [
      { path: '', redirectTo: 'offer', pathMatch: 'full' }, //Cambiar
      { path: 'login', component: LoginComponent },
      { path: 'offer', component: OfferComponent },
      { path: 'offer/:id', component: OfferDetailComponent },
      { path: 'contact', component: ContactComponent },
      { path: 'register', component: RegisterComponent },
      { path: 'formulario/:id', component: FormStepperComponent },
      { path: 'form-status', component: FormStatusComponent },
      { path: 'form-create', component: FormCreateComponent },
      { path: 'responses/:id', component: ResponsesTableComponent },
      { path: 'about-us', component: AboutUsComponent },
      { path: 'centers', component: CentersComponent },
      { path: 'not-found', component: NotFoundComponent },
      { path: 'profile', component: ProfileComponent },
      { path: '**', redirectTo: 'not-found' },
      {
        path: 'login',
        loadComponent: () =>
          import('./features/login/login.component').then((m) => m.LoginComponent),
      },
    ],
  },
];
