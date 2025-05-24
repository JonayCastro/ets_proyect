import { EventEmitter, Injectable, Output } from '@angular/core';
import FavoritesEvent from '../../enums/favorites-event';

@Injectable({
  providedIn: 'root'
})
export class FavoritesEmiterService {

  @Output() favoriteAddedEmitter: EventEmitter<FavoritesEvent> = new EventEmitter()

  constructor() { }
}
