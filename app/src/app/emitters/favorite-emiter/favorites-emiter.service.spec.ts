import { TestBed } from '@angular/core/testing';

import { FavoritesEmitterService } from './favorites-emiter.service';

describe('FavoritesEmiterService', () => {
  let service: FavoritesEmitterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FavoritesEmitterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
