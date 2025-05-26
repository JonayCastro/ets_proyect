import { Injectable } from '@angular/core';
import { Subject, Observable } from 'rxjs';
import FavoritesEvent from '../../enums/favorites-event';

@Injectable({
  providedIn: 'root'
})
export class FavoritesEmitterService {

  private favoriteAddedSubject = new Subject<FavoritesEvent>();
  private favoriteRemovedSubject = new Subject<FavoritesEvent>();

  favoriteAdded$: Observable<FavoritesEvent> = this.favoriteAddedSubject.asObservable();
  favoriteRemoved$: Observable<FavoritesEvent> = this.favoriteRemovedSubject.asObservable();

  constructor() {}

  favoriteAdded(): void {
    this.favoriteAddedSubject.next(FavoritesEvent.ADD);
  }

  favoriteRemoved(): void {
    this.favoriteRemovedSubject.next(FavoritesEvent.REMOVE);
  }
}