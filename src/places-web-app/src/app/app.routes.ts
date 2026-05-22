import { Routes } from '@angular/router';
import { AuthLayoutComponent } from '@layouts/auth-layout/auth-layout.component';
import { MainLayoutComponent } from '@layouts/main-layout/main-layout.component';
import { authGuard } from './core/guards/auth/auth.guard';
import { authChildsGuard } from './core/guards/auth/auth-childs.guard';
import { AdminRoutingModule } from '@modules/admin/admin-routing-module';
import { AdminLayoutComponent } from '@layouts/admin-layout/admin-layout.component';

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
        loadChildren: () => import('@modules/main/main.module').then(m => m.MainModule),
        canActivate: [authGuard], // Protege la ruta principal para que solo los usuarios autenticados puedan acceder
        canActivateChild: [authChildsGuard] // Protege las rutas hijas para que solo los usuarios autenticados puedan acceder a ellas
    },
    {
        path: 'admin',
        component: AdminLayoutComponent,
        loadChildren: () => import('@modules/admin/admin.module').then(m => m.AdminModule)
    }
];
