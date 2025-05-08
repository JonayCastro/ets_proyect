import { Component, inject } from '@angular/core';
import {MatSnackBar} from '@angular/material/snack-bar';
import { LoginService } from '../../services/login-services/login.service';
import UserDTO from '../../dto/user-dto';
import { Router } from '@angular/router';
import { DataTransferService } from '../../services/data-transfer/data-transfer.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {

  visiblePassword: boolean = false;
  userDTO: UserDTO = new UserDTO({});
  private _snackBar = inject(MatSnackBar);

  constructor(private loginServices: LoginService, private router: Router, private dataTransferService: DataTransferService) {}


  login() {
    this.loginServices.login(this.userDTO).subscribe({
      next: (response) => {
        const token: string = response.token;
        this.saveToken(token);
        this.router.navigate(['/dashboard']);
      },
      error: (error) => {
        this.showSnakcBar('Login failed. Please check your credentials.');
      }
    })

  }

  togglePasswordVisibility() {
    this.visiblePassword = !this.visiblePassword;
  }


  saveToken(token: string) {
    localStorage.setItem('token', token);
  }

  createAccount() {
    this.loginServices.createAccount(this.userDTO).subscribe({
      next: (response) => {
        this.dataTransferService.setData(response.url);
        const token: string = response.token;
        this.saveToken(token);
        this.showSnakcBar('Account created successfully');
        this.router.navigate(['/dashboard/subscription']);
      },
      error: (error) => {
        this.showSnakcBar('Account creation failed')
      }
    })
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
