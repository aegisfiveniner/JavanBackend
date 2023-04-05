import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoSparepartComponent } from './info-sparepart.component';

describe('InfoSparepartComponent', () => {
  let component: InfoSparepartComponent;
  let fixture: ComponentFixture<InfoSparepartComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InfoSparepartComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InfoSparepartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
