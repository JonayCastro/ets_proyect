import { HttpClient, HttpParams } from '@angular/common/http';
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

  public addFavorite(sneakerId: number): Observable<{ message: string }> {
    const url: string = Utils.urlConstructorWithId({
      paths: [Paths.FAVORITE_PATH, Paths.ADD_PATH],
      id: sneakerId
    });

    return this.http.put<{ message: string }>(url, null);
  }

  public removeFavorite(favoriteId: number): Observable<{message: string}> {
    const url: string = Utils.urlConstructorWithId({
      paths: [Paths.FAVORITE_PATH, Paths.REMOVE_PATH],
      id: favoriteId
    });

    return this.http.delete<{ message: string }>(url);
  }
}
