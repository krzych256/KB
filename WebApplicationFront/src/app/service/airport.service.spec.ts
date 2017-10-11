import { TestBed, inject } from '@angular/core/testing';

import { AirportService } from './airport.service';

describe('AirportServiceService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AirportService]
    });
  });

  it('should be created', inject([AirportService], (service: AirportService) => {
    expect(service).toBeTruthy();
  }));
});
