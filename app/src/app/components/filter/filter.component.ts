import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
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
export class FilterComponent implements OnInit {
  
  filters = new FormControl('');
  filterDto: FiltersDTO = new FiltersDTO();
  sneakerFiltersList: any[] = Filters.getFiltersList();
  selectedFilterKey: string = '';
  minRangePrice: number = 0;
  maxRangePrice: number = 500;
  sneakerFilteredList: SneakerDTO[] = [];
  favoritesFilteredList: FavoriteDTO[] = [];
  productsUrl: string = 'products';
  favoritesUrl: string = 'favorites';

  constructor(private router: Router) {}
  
  ngOnInit(): void {
    this.filters.setValue(Filters.getFiltersList()[0]);
    this.selectedFilterKey = Filters.getFiltersList()[0].key;
    console.log('oninit', this.selectedFilterKey);
  }

  addFilter(filter: string): void {
    Filters.addFilter(filter);
    this.selectedFilterKey = filter;
    console.log('add', this.selectedFilterKey);
  }

  clearFilters(): void {
    Filters.clearFilters();
    this.filterDto.brandFilter = '';
    this.filterDto.minPriceFilter = null;
    this.filterDto.maxPriceFilter = null;
  }

  getFilteredList(): void {
    const componentUrl: string = this.router.url.split('/').pop() || '';

    console.log('filter dto', this.filterDto);

    if (componentUrl === this.productsUrl) {
      console.log('products');
    } else {
      console.log('favorites');
    }
    
  }



}
