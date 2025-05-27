import { Component, inject, OnInit } from '@angular/core';
import SneakerDTO from '../../dto/sneaker-dto';
import { SneakerService } from '../../services/sneakers/sneaker.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FavoritesEmitterService } from '../../emitters/favorite-emiter/favorites-emiter.service';
import FavoritesEvent from '../../enums/favorites-event';
import { FiltersEmitterService } from '../../emitters/filter-emitter/filters-emitter.service';
import FiltersEvents from '../../enums/filter-events';
import { FilteredSearchService } from '../../services/filtered-search/filtered-search.service';
import Filters from '../../config/filters/filter';

@Component({
  selector: 'app-sneakers-list',
  templateUrl: './sneakers-list.component.html',
  styleUrl: './sneakers-list.component.scss'
})
export class SneakersListComponent implements OnInit {

  sneakersDtoList: SneakerDTO[] = [];
  private _snackBar = inject(MatSnackBar);

  constructor(private sneakerService: SneakerService, 
    private favoriteEmitterService: FavoritesEmitterService,
    private filtersEmitterService: FiltersEmitterService,
    private filteredSearchService: FilteredSearchService
  ) { }

  ngOnInit(): void {
    this.filtersEmitterService.filterEvents$.subscribe({
      next: (event) => {
        if (event === FiltersEvents.FILTERS_ADDED) {
          this.getFilteredSneakers();
        } else if (event === FiltersEvents.FILTERS_REMOVED) {
          this.getSneakers();
        }
      }
    });

    this.favoriteEmitterService.favoriteEvents$.subscribe({
      next: (event: FavoritesEvent) => {
        if (event === FavoritesEvent.ADD) {
          this.unFilteredCharge();
        }
      }
    });
    this.unFilteredCharge();
  }

  unFilteredCharge(): void {
    if (!Filters.hasFiltersApplied()) {
      this.getSneakers();}
    else {
      this.getFilteredSneakers();
    }
  }

  getSneakers(): void {
    this.sneakerService.getSneakers().subscribe({
      next: (sneakers) => {
        this.sneakersDtoList = sneakers;
        this.showSnakcBar('Lista de productos cargada');
      },
      error: (error) => {
        this.showSnakcBar('No se cargado la lista de productos');
      }
    });

  }

  getFilteredSneakers(): void {
    this.filteredSearchService.getFilteredSneakersList().subscribe({
      next: (sneakers: SneakerDTO[]) => {
        this.sneakersDtoList = sneakers;
        this.showSnakcBar('Filtered Sneakers fetched successfully');
      } ,
      error: (error) => {
        this.showSnakcBar('Error fetching filtered sneakers');
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
