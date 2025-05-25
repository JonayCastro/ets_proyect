import FiltersInterface from "../interface/filters-interface";

export default class FiltersDTO implements FiltersInterface{
    key: string;
    brandFilter: string;
    minPriceFilter: number | null;
    maxPriceFilter: number | null;

    constructor (data: Partial<FiltersInterface> = {}) {
        this.key = data.key || '';
        this.brandFilter = data.brandFilter || '';
        this.minPriceFilter = data.minPriceFilter || null;
        this.maxPriceFilter = data.maxPriceFilter || null;
    }
    
}