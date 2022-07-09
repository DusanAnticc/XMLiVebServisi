import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MakePotvrdaComponent } from './make-potvrda.component';

describe('MakePotvrdaComponent', () => {
  let component: MakePotvrdaComponent;
  let fixture: ComponentFixture<MakePotvrdaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MakePotvrdaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MakePotvrdaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
