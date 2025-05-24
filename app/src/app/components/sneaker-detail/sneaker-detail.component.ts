import { Component, inject, Input, OnInit } from '@angular/core';
import SneakerDTO from '../../dto/sneaker-dto';
import { FavoritesService } from '../../services/favorite/favorites.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FavoritesEmitterService } from '../../emitters/favorite-emiter/favorites-emiter.service';

@Component({
  selector: 'app-sneaker-detail',
  templateUrl: './sneaker-detail.component.html',
  styleUrls: ['./sneaker-detail.component.scss']
})
export class SneakerDetailComponent implements OnInit {

  @Input() sneakerDto!: SneakerDTO;
  sneakerLink: string = '';
  baseUrl: string = 'https://www.fittestfreakest.es/';
  private _snackBar = inject(MatSnackBar);
  

  constructor(private favoriteService: FavoritesService, private favoriteEmitterService: FavoritesEmitterService) { }

  ngOnInit(): void {
    this.sneakerLink = this.baseUrl + this.sneakerDto.link;
  }

  addFavorite(sneakerId: number): void {
    this.favoriteService.addFavorite(sneakerId).subscribe({
      next: (response) => {
        this.favoriteEmitterService.favoriteAdded();
        this.showSnakcBar('Zapatilla añadida a favoritos');
      },
      error: (error) => {
        this.showSnakcBar('No se ha podido añadir la zapatilla a favoritos');
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
