import { TestBed } from '@angular/core/testing';

import { SaglasnostService } from './services.service';

describe('SaglasnostService', () => {
  let service: SaglasnostService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SaglasnostService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
