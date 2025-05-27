import { TestBed } from '@angular/core/testing';

import { FilteredSearchService } from './filtered-search.service';

describe('FilteredSearchService', () => {
  let service: FilteredSearchService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FilteredSearchService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
