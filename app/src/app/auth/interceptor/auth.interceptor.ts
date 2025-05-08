import { HttpEvent, HttpHandler, HttpInterceptor, HttpInterceptorFn, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { EMPTY, Observable } from 'rxjs';
import { jwtDecode } from 'jwt-decode';



@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private router: Router) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const token = localStorage.getItem('token');

    if (!token || this.isTokenExpired(token)) {
      localStorage.removeItem('token');
      this.router.navigate(['/login']);
      return EMPTY;
    }

    const cloned = request.clone({
      headers: request.headers.set('Authorization', `${token}`)
    });
    return next.handle(cloned);
  }

  private isTokenExpired(token: string): boolean {
    try {
      const decoded: any = jwtDecode(token);
      const exp = decoded.exp;
      return (Date.now() >= exp * 1000);
    } catch (e) {
      return true;
    }
  }
}

