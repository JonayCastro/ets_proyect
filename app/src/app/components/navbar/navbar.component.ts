import { Component } from '@angular/core';
import { Router } from '@angular/router';
import AppConstants from '../../config/app-constants';
/**
 * NavbarComponent is responsible for displaying the navigation bar of the application.
 * It provides navigation links to different sections of the application and handles user logout.
 */
@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {

  constructor(private router: Router) { }

  logout() {
    this.router.navigate(['/login']);
    localStorage.removeItem('token');
    localStorage.removeItem(AppConstants.STORAGE_FILTERS_KEY);
  }
}
