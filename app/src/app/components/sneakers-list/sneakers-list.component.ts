import { Component, inject, OnInit } from '@angular/core';
import SneakerDTO from '../../dto/sneaker-dto';
import { SneakerService } from '../../services/sneakers/sneaker.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FavoritesEmitterService } from '../../emitters/favorite-emiter/favorites-emiter.service';
import FavoritesEvent from '../../enums/favorites-event';

@Component({
  selector: 'app-sneakers-list',
  templateUrl: './sneakers-list.component.html',
  styleUrl: './sneakers-list.component.scss'
})
export class SneakersListComponent implements OnInit {

  sneakersDtoList: SneakerDTO[] = [];
  private _snackBar = inject(MatSnackBar);

  constructor(private sneakerService: SneakerService, private favoriteEmitterService: FavoritesEmitterService) { }

  ngOnInit(): void {
    this.favoriteEmitterService.favoriteAdded$.subscribe({
      next: (event: FavoritesEvent) => {
        if (event === FavoritesEvent.ADD) {
          this.getSneakers();
        }
      }
    });
    this.getSneakers();
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

  showSnakcBar(message: string) {
    this._snackBar.open(message, 'Close', {
      duration: 2000,
      verticalPosition: 'bottom',
      horizontalPosition: 'right',
      panelClass: ['snackbar']
    });
  }
}
