import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoMaintenanceComponent } from './info-maintenance.component';

describe('InfoMaintenanceComponent', () => {
  let component: InfoMaintenanceComponent;
  let fixture: ComponentFixture<InfoMaintenanceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InfoMaintenanceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InfoMaintenanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
