import { TestBed } from '@angular/core/testing';

import { FavoritesEmiterService } from './favorites-emiter.service';

describe('FavoritesEmiterService', () => {
  let service: FavoritesEmiterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FavoritesEmiterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
