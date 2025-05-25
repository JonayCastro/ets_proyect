import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { provideHttpClient, withFetch, withInterceptors } from '@angular/common/http';
import { AuthInterceptor } from './auth/interceptor/auth.interceptor';
import { SubscriptionComponent } from './components/subscription/subscription.component';
import { SneakerDetailComponent } from './components/sneaker-detail/sneaker-detail.component';
import { SneakersListComponent } from './components/sneakers-list/sneakers-list.component';
import { FavoritesListComponent } from './components/favorites-list/favorites-list.component';
import { FavoriteDetailComponent } from './components/favorite-detail/favorite-detail.component';
import { MatSelectModule } from '@angular/material/select';
import { FilterComponent } from './components/filter/filter.component';
import { NavbarComponent } from './components/navbar/navbar.component';

/**
 * AppModule is the main module of the application.
 * It imports all the necessary modules and components.
 * It declares the components used in the application.
 * It provides the necessary services for HTTP requests and animations.
 * It bootstraps the AppComponent.
 * This module is used for client-side rendering of the application.
 * It is the entry point for the Angular application.
 * It is responsible for setting up the application and its dependencies.
 */

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    DashboardComponent,
    NavbarComponent,
    SidebarComponent,
    SubscriptionComponent,
    SneakerDetailComponent,
    SneakersListComponent,
    FavoritesListComponent,
    FavoriteDetailComponent,
    FilterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatToolbarModule,
    MatIconModule,
    MatSidenavModule,
    MatListModule,
    MatTableModule,
    MatFormFieldModule,
    MatSelectModule
  ],
  providers: [
    provideClientHydration(),
    provideAnimationsAsync(),
    provideHttpClient(
      withFetch(),
      withInterceptors([AuthInterceptor])
    )
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
