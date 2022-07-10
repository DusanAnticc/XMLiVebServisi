import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MakeInteresovanjeComponent } from './make-interesovanje.component';

describe('MakeInteresovanjeComponent', () => {
  let component: MakeInteresovanjeComponent;
  let fixture: ComponentFixture<MakeInteresovanjeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MakeInteresovanjeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MakeInteresovanjeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

