import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth.guard';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },
  {
    path: 'login',
    loadComponent: () =>
      import('./auth/login/login.component').then((m) => m.LoginComponent)
  },
  {
    path: 'register',
    loadComponent: () =>
      import('./auth/register/register.component').then((m) => m.RegisterComponent)
  },
  {
    path: 'applications',
    canActivate: [authGuard],
    loadComponent: () =>
      import('./applications/application-list/application-list.component').then(
        (m) => m.ApplicationListComponent
      )
  },
  {
    path: '**',
    redirectTo: 'login'
  }
];
