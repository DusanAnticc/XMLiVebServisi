import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MakeIzvestajComponent } from './make-izvestaj.component';

describe('MakeIzvestajComponent', () => {
  let component: MakeIzvestajComponent;
  let fixture: ComponentFixture<MakeIzvestajComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [MakeIzvestajComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MakeIzvestajComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
