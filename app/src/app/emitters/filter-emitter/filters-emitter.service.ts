import { Injectable } from '@angular/core';
import FiltersEvents from '../../enums/filter-events';
import { Observable, Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FiltersEmitterService {
  private filterEventsSubject = new Subject<FiltersEvents>();

  filterEvents$: Observable<FiltersEvents> = this.filterEventsSubject.asObservable();

  filtersAdded(): void {
    this.filterEventsSubject.next(FiltersEvents.FILTERS_ADDED);
  }

  filtersRemoved(): void {
    this.filterEventsSubject.next(FiltersEvents.FILTERS_REMOVED);
  }
}
