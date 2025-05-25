import { Component } from '@angular/core';
import Filters from '../../config/filters/filter';
import SneakerDTO from '../../dto/sneaker-dto';
import FavoriteDTO from '../../dto/favorite-dto';
import { Router } from '@angular/router';
import FiltersDTO from '../../dto/filters-dto';


@Component({
  selector: 'app-filter',
  templateUrl: './filter.component.html',
  styleUrls: ['./filter.component.scss']
})
export class FilterComponent{
  
  filterDto: FiltersDTO = Filters.getFiltersApplied();

  filterApplied: FiltersDTO = this.filterDto;

  sneakerFiltersList: any[] = Filters.getFiltersList();
  
  minRangePrice: number = 0;
  maxRangePrice: number = 500;
  
  productsUrl: string = 'products';
  favoritesUrl: string = 'favorites';

  sneakerFilteredList: SneakerDTO[] = [];
  favoritesFilteredList: FavoriteDTO[] = [];

  constructor(private router: Router) {}
  
  saveFilters(): void {
    Filters.addFilter(this.filterDto);
  }

  addFilter(filter: FiltersDTO): void {
    Filters.addFilter(this.filterDto);
  }

  clearFilters(): void {
    Filters.clearFilters();
    this.filterDto.key = '';
    this.filterDto.brandFilter = '';
    this.filterDto.minPriceFilter = null;
    this.filterDto.maxPriceFilter = null;
  }

  getFilteredList(): void {
    const componentUrl: string = this.router.url.split('/').pop() || '';

    if (componentUrl === this.productsUrl) {
      console.log('products');
    } else {
      console.log('favorites');
    }
    
  }



}
