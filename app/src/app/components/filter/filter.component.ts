import { Component } from '@angular/core';
import Filters from '../../config/filters/filter';
import FiltersDTO from '../../dto/filters-dto';
import { FiltersEmitterService } from '../../emitters/filter-emitter/filters-emitter.service';


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

  constructor(private filtersEmitterService: FiltersEmitterService) {}
  
  saveFilters(): void {
    Filters.addFilter(this.filterDto);
  }

  addFilter(filter: FiltersDTO): void {
    Filters.addFilter(this.filterDto);
  }

  hasAnyFilter(): boolean {
    return Filters.hasFiltersApplied() ;
  }

  clearFilters(): void {
    Filters.clearFilters();
    this.filterDto.key = '';
    this.filterDto.brandFilter = '';
    this.filterDto.minPriceFilter = null;
    this.filterDto.maxPriceFilter = null;
    this.filtersEmitterService.filtersRemoved();
  }

  isInputDisabled(): boolean {
    return !this.filterDto.key;
  }

  filterList(): void {
    this.filtersEmitterService.filtersAdded();
  }
}
