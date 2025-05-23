import { Component, inject, OnInit } from '@angular/core';
import FavoriteDTO from '../../dto/favorite-dto';
import { FavoritesService } from '../../services/favorite/favorites.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-favorites',
  templateUrl: './favorites-list.component.html',
  styleUrls: ['./favorites-list.component.scss']
})
export class FavoritesListComponent implements OnInit{

  favoriteDtoList: FavoriteDTO[] = []
  private _snackBar = inject(MatSnackBar);
  
  constructor(private favoritesService: FavoritesService) {}
  
  ngOnInit(): void {
    this.getFavorites();
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

  showSnakcBar(message: string) {
    this._snackBar.open(message, 'Close', {
      duration: 2000,
      verticalPosition: 'bottom',
      horizontalPosition: 'right',
      panelClass: ['snackbar']
    });
  }

}
