import { TestBed } from '@angular/core/testing';

import { PregledDokumentaService } from './pregled-dokumenta.service';

describe('PregledDokumentaService', () => {
  let service: PregledDokumentaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PregledDokumentaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
