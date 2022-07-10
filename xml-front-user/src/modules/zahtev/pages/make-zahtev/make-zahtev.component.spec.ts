import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MakeZahtevComponent } from './make-zahtev.component';

describe('MakeZahtevComponent', () => {
  let component: MakeZahtevComponent;
  let fixture: ComponentFixture<MakeZahtevComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MakeZahtevComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MakeZahtevComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
