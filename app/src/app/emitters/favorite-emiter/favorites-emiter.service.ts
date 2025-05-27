import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';
import FavoritesEvent from '../../enums/favorites-event';

@Injectable({
  providedIn: 'root'
})
export class FavoritesEmitterService {

  private favoriteEventSubject = new Subject<FavoritesEvent>();

  favoriteEvents$: Observable<FavoritesEvent> = this.favoriteEventSubject.asObservable();

  constructor() {}

  favoriteAdded(): void {
    this.favoriteEventSubject.next(FavoritesEvent.ADD);
  }

  favoriteRemoved(): void {
    this.favoriteEventSubject.next(FavoritesEvent.REMOVE);
  }
}