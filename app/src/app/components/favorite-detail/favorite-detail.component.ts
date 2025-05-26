import { Component, inject, Input } from '@angular/core';
import FavoriteDTO from '../../dto/favorite-dto';
import { MatSnackBar } from '@angular/material/snack-bar';
import AppConstants from '../../config/app-constants';
import { FavoritesService } from '../../services/favorite/favorites.service';
import { FavoritesEmitterService } from '../../emitters/favorite-emiter/favorites-emiter.service';

@Component({
  selector: 'app-favorite-detail',
  templateUrl: './favorite-detail.component.html',
  styleUrl: './favorite-detail.component.scss'
})
export class FavoriteDetailComponent {

  @Input() favoriteDto!: FavoriteDTO;
  favoriteLink: string = '';
  baseUrl: string = AppConstants.SNEAKER_BASE_URL;
  private _snackBar = inject(MatSnackBar);
  

  constructor(private favoriteService: FavoritesService, private favoriteEmitterService: FavoritesEmitterService) { }

  ngOnInit(): void {
    this.favoriteLink = this.baseUrl + this.favoriteDto.link;
  }

  removeFavorite(favoriteId: number): void {
    this.favoriteService.removeFavorite(favoriteId).subscribe({
      next: (response) => {
        this.favoriteEmitterService.favoriteRemoved();
        this.showSnakcBar('Zapatilla eliminada de favoritos');
      },
      error: (error) => {
        this.showSnakcBar('No se ha podido eliminar la zapatilla de favoritos');
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
