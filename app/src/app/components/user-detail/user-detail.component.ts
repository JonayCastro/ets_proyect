import { Component } from '@angular/core';

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
