import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import FiltersDTO from '../../dto/filters-dto';
import SneakerDTO from '../../dto/sneaker-dto';
import { Observable } from 'rxjs';
import Utils from '../../utils/utils';
import Paths from '../../config/paths';
import FavoriteDTO from '../../dto/favorite-dto';
import Filters from '../../config/filters/filter';
import OffersDTO from '../../dto/offers-dto';

@Injectable({
  providedIn: 'root'
})
export class FilteredSearchService {

  constructor(private http: HttpClient) { }

  public getFilteredOffers(): Observable<OffersDTO[]> {
    const storedFilters: FiltersDTO = this.getStoredFiltered();
    let basePathsList: string[] = [Paths.FAVORITE_PATH, Paths.OFFERS_PATH, Paths.FILTERED_PATH];
    const pathList: string[] = this.addFilterType(basePathsList, storedFilters.key!);
    const url: string = Utils.urlConstructorWithoutId({
      paths: pathList
    });
    return this.http.post<OffersDTO[]>(url, storedFilters, { observe: 'body' });
  }

  public getFilteredSneakersList(): Observable<SneakerDTO[]> {

    const storedFilters: FiltersDTO = this.getStoredFiltered();

    let basePathsList: string[] = [Paths.SNEAKERS_PATH, Paths.FILTERED_PATH]

    const pathList: string[] = this.addFilterType(basePathsList, storedFilters.key!);

    const url: string = Utils.urlConstructorWithoutId({
          paths: pathList
        });

    return this.http.post<SneakerDTO[]>(url, storedFilters, { observe: 'body' });

  }

  public getFilteredFavoriteList(): Observable<FavoriteDTO[]> {

    const storedFilters: FiltersDTO = this.getStoredFiltered();

    let basePathsList: string[] = [Paths.FAVORITE_PATH, Paths.FILTERED_PATH]

    const pathList: string[] = this.addFilterType(basePathsList, storedFilters.key!);

    const url: string = Utils.urlConstructorWithoutId({
          paths: pathList
        });

    return this.http.post<FavoriteDTO[]>(url, storedFilters, { observe: 'body' });

  }

  private getStoredFiltered(): FiltersDTO{
    return Filters.getFiltersApplied();
  }

  private addFilterType(pathList: string[], filterKey: string): string[] {
    
    if (filterKey === 'brand') {
      pathList.push(Paths.BRAND_PATH);
    } else {
      pathList.push(Paths.PRICE_PATH);
    }

    return pathList;
  }
}
