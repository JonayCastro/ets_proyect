import { Component, inject, OnInit } from '@angular/core';
import OffersDTO from '../../dto/offers-dto';
import { OffersServices } from '../../services/offers/offers.service';
import { FiltersEmitterService } from '../../emitters/filter-emitter/filters-emitter.service';
import { FilteredSearchService } from '../../services/filtered-search/filtered-search.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import Filters from '../../config/filters/filter';
import FiltersEvents from '../../enums/filter-events';
import FavoritesEvent from '../../enums/favorites-event';

@Component({
  selector: 'app-offers-list',
  templateUrl: './offers-list.component.html',
  styleUrls: ['./offers-list.component.scss']
})
export class OffersListComponent implements OnInit {

  offersDtoList: OffersDTO[] = [];
  private _snackBar = inject(MatSnackBar);


  constructor(private offersServices: OffersServices,
    private filtersEmitterService: FiltersEmitterService,
    private filteredSearchService: FilteredSearchService) { }

  ngOnInit(): void {
    this.filtersEmitterService.filterEvents$.subscribe({
      next: (event) => {
        if (event === FiltersEvents.FILTERS_ADDED) {
          this.getFilteredOffers();
        } else if (event === FiltersEvents.FILTERS_REMOVED) {
          this.getOffers();
        }
      }
    });

    this.unFilteredCharge();
  }

  unFilteredCharge(): void {
    if (!Filters.hasFiltersApplied()) {
      this.getOffers();
    } else {
      this.getFilteredOffers();
    }
  }

  getOffers(): void {
    this.offersServices.getOffers().subscribe({
      next: (offers) => {
        this.offersDtoList = offers;
        this.showSnakcBar('Offers loaded successfully');
      },
      error: (error) => {
        this.showSnakcBar(`Error loading offers: ${error.message}`);
      }
    });
  }

  getFilteredOffers(): void {
    this.filteredSearchService.getFilteredOffers().subscribe({
      next: (offers) => {
        this.offersDtoList = offers;
        this.showSnakcBar('Filtered offers loaded successfully');
      },
      error: (error) => {
        this.showSnakcBar(`Error loading filtered offers: ${error.message}`);
      }
    });
  }

  showSnakcBar(message: string) {
    this._snackBar.open(message, 'Close', {
      duration: 2000,
      verticalPosition: 'bottom',
      horizontalPosition: 'right',
      panelClass: ['snackbar']
    });
  }


}
