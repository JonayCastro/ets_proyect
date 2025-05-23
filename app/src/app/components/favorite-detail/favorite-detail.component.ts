import { Component, Input } from '@angular/core';
import FavoriteDTO from '../../dto/favorite-dto';

@Component({
  selector: 'app-favorite-detail',
  templateUrl: './favorite-detail.component.html',
  styleUrl: './favorite-detail.component.scss'
})
export class FavoriteDetailComponent {

  @Input() favoriteDto!: FavoriteDTO;
    favoriteLink: string = '';
    baseUrl: string = 'https://www.fittestfreakest.es/';
  
    constructor() {}
    
    ngOnInit(): void {
      this.favoriteLink = this.baseUrl + this.favoriteDto.link;
    }

}
