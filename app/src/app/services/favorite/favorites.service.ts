import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import FavoriteDTO from '../../dto/favorite-dto';
import { Observable } from 'rxjs';
import Utils from '../../utils/utils';
import Paths from '../../config/paths';

@Injectable({
  providedIn: 'root'
})
export class FavoritesService {

  constructor(private http: HttpClient) { }

  public getFavorites(): Observable<FavoriteDTO[]> {
    const url: string = Utils.urlConstructorWithoutId({
      paths: [Paths.FAVORITE_PATH, Paths.LIST_PATH]
    });

    return this.http.get<FavoriteDTO[]>(url);
  }
}
