import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { AuthGuard } from './auth/guard/guards.guard';
import { SubscriptionComponent } from './components/subscription/subscription.component';
import { SneakersListComponent } from './components/sneakers-list/sneakers-list.component';
import { FavoritesListComponent } from './components/favorites-list/favorites-list.component';
import { OffersListComponent } from './components/offers-list/offers-list.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [AuthGuard],
    children: [
      { path: 'subscription', component: SubscriptionComponent},
      { path: '', redirectTo: 'products', pathMatch: 'full' },
      { path: 'products', component: SneakersListComponent },
      { path: 'favorites', component: FavoritesListComponent },
      { path: 'offers', component: OffersListComponent}
    ]
  },
  { path: '**', redirectTo: 'login' }
];

/**
 * Module for routing in the application.
 * It defines the routes for the application and imports the RouterModule.
 * The routes include the login page, dashboard, user list, and user detail pages.
 */

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
