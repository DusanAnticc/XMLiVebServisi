import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PregledDokumentaComponent } from './pregled-dokumenta.component';

describe('PregledDokumentaComponent', () => {
  let component: PregledDokumentaComponent;
  let fixture: ComponentFixture<PregledDokumentaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PregledDokumentaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PregledDokumentaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
