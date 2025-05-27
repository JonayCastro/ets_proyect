import FiltersInterface from "../interface/filters-interface";

export default class FiltersDTO implements FiltersInterface{
    key: string | null;
    brandFilter: string | null;
    minPriceFilter: number | null;
    maxPriceFilter: number | null;

    constructor (data: Partial<FiltersInterface> = {}) {
        this.key = data.key || null;
        this.brandFilter = data.brandFilter || null;
        this.minPriceFilter = data.minPriceFilter || null;
        this.maxPriceFilter = data.maxPriceFilter || null;
    }
    
}