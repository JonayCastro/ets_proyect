import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import SneakerDTO from '../../dto/sneaker-dto';
import Utils from '../../utils/utils';
import Paths from '../../config/paths';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SneakerService {

  constructor(private http: HttpClient) { }

  public getSneakers(): Observable<SneakerDTO[]> {
    const url: string = Utils.urlConstructorWithoutId({
      paths: [Paths.SNEAKERS_PATH, Paths.STORED_PRODUCTS_PATH]
    });

    return this.http.get<SneakerDTO[]>(url);
  }
}
