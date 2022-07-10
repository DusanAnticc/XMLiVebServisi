import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MakeSaglasnostComponent } from './make-saglasnost.component';

describe('MakeSaglasnostComponent', () => {
  let component: MakeSaglasnostComponent;
  let fixture: ComponentFixture<MakeSaglasnostComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MakeSaglasnostComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MakeSaglasnostComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
