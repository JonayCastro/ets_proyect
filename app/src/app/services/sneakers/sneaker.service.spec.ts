import { TestBed } from '@angular/core/testing';

import { SneakerServicesService } from './sneaker.service';

describe('SneakerServicesService', () => {
  let service: SneakerServicesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SneakerServicesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
