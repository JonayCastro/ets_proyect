import { FiltersType } from "./filter-types";
import FiltersDTO from "../../dto/filters-dto";

export default class Filters {

  private static readonly STORAGE_KEY = 'appliedFilters';
  private static _appliedFilters: FiltersDTO | null = null;

  static getFiltersList(): any {
    return FiltersType.filters;
  }

  static addFilter(filterDto: FiltersDTO): void {
    this._appliedFilters = filterDto;
    if (typeof window !== 'undefined') {
      localStorage.setItem(this.STORAGE_KEY, JSON.stringify(filterDto));
    }
  }

  static clearFilters(): void {
    this._appliedFilters = new FiltersDTO();
    if (typeof window !== 'undefined') {
      localStorage.removeItem(this.STORAGE_KEY);
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
      const stored = localStorage.getItem(this.STORAGE_KEY);
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
