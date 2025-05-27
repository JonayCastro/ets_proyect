import { FiltersType } from "./filter-types";
import FiltersDTO from "../../dto/filters-dto";
import AppConstants from "../app-constants";

export default class Filters {

  private static _appliedFilters: FiltersDTO | null = null;

  static getFiltersList(): any {
    return FiltersType.filters;
  }

  static hasFiltersApplied(): boolean {
    return !!(
      Filters._appliedFilters &&
      (
        Filters._appliedFilters.key ||
        Filters._appliedFilters.brandFilter ||
        Filters._appliedFilters.minPriceFilter != null ||
        Filters._appliedFilters.maxPriceFilter != null
      )
    );
  }

  static addFilter(filterDto: FiltersDTO): void {
    this._appliedFilters = filterDto;
    if (typeof window !== 'undefined') {
      localStorage.setItem(AppConstants.STORAGE_FILTERS_KEY, JSON.stringify(filterDto));
    }
  }

  static clearFilters(): void {
    this._appliedFilters = new FiltersDTO();
    if (typeof window !== 'undefined') {
      localStorage.removeItem(AppConstants.STORAGE_FILTERS_KEY);
    }
  }

  static getFiltersApplied(): FiltersDTO {
    if (!this._appliedFilters) {
      this._appliedFilters = this.loadFromStorage();
    }
    return this._appliedFilters;
  }

  private static loadFromStorage(): FiltersDTO {
    if (typeof window !== 'undefined') {
      const stored = localStorage.getItem(AppConstants.STORAGE_FILTERS_KEY);
      if (stored) {
        try {
          return Object.assign(new FiltersDTO(), JSON.parse(stored));
        } catch (e) {
          console.error("Error parsing stored filters", e);
        }
      }
    }
    return new FiltersDTO();
  }
}
