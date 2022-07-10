import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SaglasnostComponent } from './saglasnost.component';

describe('SaglasnostComponent', () => {
  let component: SaglasnostComponent;
  let fixture: ComponentFixture<SaglasnostComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SaglasnostComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SaglasnostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
