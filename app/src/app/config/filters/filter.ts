import { filter } from "rxjs";
import { FiltersType } from "./filter-types";

export default class Filters {
    
    private static _appliedFilters: string[] = [];

    static getFiltersList(): any {
        return FiltersType.filters;
    }

    static addFilter(filterKey: string): void {
        if (filterKey) {
            this._appliedFilters = [];
            this._appliedFilters.push(filterKey);
        }
    }

    static clearFilters(): void {
        this._appliedFilters = [];
        console.log('clear', this._appliedFilters);

    }
}