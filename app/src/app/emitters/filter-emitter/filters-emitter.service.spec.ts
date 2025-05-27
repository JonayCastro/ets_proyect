import { TestBed } from '@angular/core/testing';

import { FiltersEmitterService } from './filters-emitter.service';

describe('FiltersEmitterService', () => {
  let service: FiltersEmitterService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(FiltersEmitterService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
