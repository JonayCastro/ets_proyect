import { HttpRequest, HttpHandlerFn, HttpEvent } from '@angular/common/http';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, EMPTY } from 'rxjs';
import { jwtDecode } from 'jwt-decode';

export const AuthInterceptor = (req: HttpRequest<any>, next: HttpHandlerFn): Observable<HttpEvent<any>> => {
  const router = inject(Router);

  if (req.url.includes('/account')) {
    return next(req);
  }

  const token = localStorage.getItem('token');

  if (!token || isTokenExpired(token)) {
    localStorage.removeItem('token');
    router.navigate(['/login']);
    return EMPTY;
  }

  const cloned = req.clone({
    headers: req.headers.set('Authorization', `${token}`)
  });

  return next(cloned);
};

function isTokenExpired(token: string): boolean {
  try {
    const decoded: any = jwtDecode(token);
    const exp = decoded.exp;
    return Date.now() >= exp * 1000;
  } catch (e) {
    return true;
  }
}
