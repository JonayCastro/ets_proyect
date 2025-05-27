import { Component, inject, OnInit } from '@angular/core';
import FavoriteDTO from '../../dto/favorite-dto';
import { FavoritesService } from '../../services/favorite/favorites.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FavoritesEmitterService } from '../../emitters/favorite-emiter/favorites-emiter.service';
import FavoritesEvent from '../../enums/favorites-event';
import { FiltersEmitterService } from '../../emitters/filter-emitter/filters-emitter.service';
import { FilteredSearchService } from '../../services/filtered-search/filtered-search.service';
import Filters from '../../config/filters/filter';
import FiltersEvents from '../../enums/filter-events';

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites-list.component.html',
  styleUrls: ['./favorites-list.component.scss']
})
export class FavoritesListComponent implements OnInit {

  favoriteDtoList: FavoriteDTO[] = []
  private _snackBar = inject(MatSnackBar);

  constructor(private favoritesService: FavoritesService,
    private favoriteEmitterService: FavoritesEmitterService,
    private filtersEmitterService: FiltersEmitterService,
    private filteredSearchService: FilteredSearchService
  ) { }

  ngOnInit(): void {
    this.filtersEmitterService.filterEvents$.subscribe({
      next: (event) => {
        if (event === FiltersEvents.FILTERS_ADDED) {
          this.getFilteredFavorites();
        } else if (event === FiltersEvents.FILTERS_REMOVED) {
          this.getFavorites();
        }
      }
    });

    this.favoriteEmitterService.favoriteEvents$.subscribe({
      next: (event: FavoritesEvent) => {
        if (event === FavoritesEvent.REMOVE) {
          this.unFilteredCharge();
        }
      }
    });
    this.unFilteredCharge();
  }

  unFilteredCharge(): void {
    if (!Filters.hasFiltersApplied()) {
      this.getFavorites();
    }
    else {
      this.getFilteredFavorites();
    }
  }

  getFavorites(): void {
    this.favoritesService.getFavorites().subscribe({
      next: (favorites) => {
        this.favoriteDtoList = favorites;
        this.showSnakcBar('Lista de favoritos cargada');
      },
      error: (error) => {
        this.showSnakcBar('No se cargado la lista de favoritos');
      }
    });
  }

  getFilteredFavorites(): void {
    this.filteredSearchService.getFilteredFavoriteList().subscribe({
      next: (favorites: FavoriteDTO[]) => {
        this.favoriteDtoList = favorites;
        this.showSnakcBar('Filtered Favorites fetched successfully');
      },
      error: (error) => {
        this.showSnakcBar('Error fetching filtered favorites');
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
