import { Routes } from '@angular/router';
import { AuthLayoutComponent } from '@layouts/auth-layout/auth-layout.component';
import { MainLayoutComponent } from '@layouts/main-layout/main-layout.component';

export const routes: Routes = [
    {
        path: '',
        redirectTo: 'main',
        pathMatch: 'full'
    },
    {
        path: 'auth',
        component: AuthLayoutComponent,
        loadChildren: () => import('@modules/auth/auth.module').then(m => m.AuthModule)
    },
    {
        path: 'main',
        component: MainLayoutComponent,
        loadChildren: () => import('@modules/main/main.module').then(m => m.MainModule)
    }
];
