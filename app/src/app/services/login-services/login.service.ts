import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import LoginDTO from '../../dto/login-dto';
import { HttpClient } from '@angular/common/http';
import UserDTO from '../../dto/user-dto';
import Utils from '../../utils/utils';
import Paths from '../../config/paths';

/**
 * LoginService is a service that handles login and account creation.
 * It provides methods to login and create an account.
 * This service uses HttpClient to make HTTP requests to the backend.
 */
@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  public login(userDto: UserDTO): Observable<LoginDTO> {
    const url: string = Utils.urlConstructorWithoutId({
      paths: [Paths.ACCOUNT_PATH, Paths.LOGIN_PATH]
    });
    return this.http.post<LoginDTO>(url, userDto, { observe: 'body' });
  }

  public createAccount(userDto: UserDTO): Observable<LoginDTO> {
    const url: string = Utils.urlConstructorWithoutId({
      paths: [Paths.ACCOUNT_PATH, Paths.CREATE_PATH]
    });
    return this.http.post<LoginDTO>(url, userDto, { observe: 'body' });
  }
}
