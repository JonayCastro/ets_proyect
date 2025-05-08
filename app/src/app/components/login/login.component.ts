import { Component, inject } from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';
import { LoginService } from '../../services/login.service';
import UserDTO from '../../dto/user-dto';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  userDTO: UserDTO = new UserDTO({});
  private _snackBar = inject(MatSnackBar);

  constructor(private loginServices: LoginService, private router: Router) {}


  login() {
    this.loginServices.login(this.userDTO).subscribe({
      next: (response) => {
        const token: string = response.token;
        localStorage.setItem('token', token);
        this.router.navigate(['/dashboard']);
      },
      error: (error) => {
        this.showSnakcBar('Login failed. Please check your credentials.');
      }
    })

  }

  createAccount() {
    this.router.navigate(['/dashboard']);
    // this.loginServices.createAccount(this.userDTO).subscribe({
    //   next: (response) => {
    //     this.router.navigate(['/dashboard']);
    //     console.log('Account created successfully', response);
    //   },
    //   error: (error) => {
    //     console.error('Account creation failed', error);
    //   }
    // })
  }

  showSnakcBar(message: string) {
    this._snackBar.open(message, 'Close', {
      duration: 2000,
      verticalPosition: 'bottom',
      horizontalPosition: 'right',
      panelClass: ['snackbar']
    });
  }
      
}
