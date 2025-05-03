import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.scss']
})
export class UserListComponent {
  displayedColumns: string[] = ['name', 'email', 'actions'];
  users = [
    { name: 'Juan Pérez', email: 'juan@example.com' },
    { name: 'Ana López', email: 'ana@example.com' }
  ];

  constructor(private router: Router) {}

  viewDetail(user: any) {
    this.router.navigate(['/dashboard/users', 1]); // reemplaza "1" por user.id si lo tienes
  }
}
