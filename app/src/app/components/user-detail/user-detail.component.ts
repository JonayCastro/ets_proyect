import { Component } from '@angular/core';

/**
 * UserDetailComponent is responsible for displaying the details of a user.
 * It includes a method to go back to the user list.
 * The user details are hardcoded for demonstration purposes.
 */
@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.scss']
})
export class UserDetailComponent {
  user = {
    name: 'Juan Pérez',
    email: 'juan@example.com',
    phone: '+34 600 123 456'
  };

  goBack() {
    console.log('Volver a la lista');
  }
}
