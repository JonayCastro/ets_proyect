import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import OffersDTO from '../../dto/offers-dto';
import Utils from '../../utils/utils';
import Paths from '../../config/paths';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OffersServices {

  constructor(private http: HttpClient) { }

  public getOffers(): Observable<OffersDTO[]> {
    const url: string = Utils.urlConstructorWithoutId({
      paths: [Paths.FAVORITE_PATH, Paths.OFFERS_PATH]
    });
    return this.http.get<OffersDTO[]>(url);
  }
}
