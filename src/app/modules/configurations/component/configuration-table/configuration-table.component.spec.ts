import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfigurationTableComponent } from './configuration-table.component';

describe('ConfigurationTableComponent', () => {
  let component: ConfigurationTableComponent;
  let fixture: ComponentFixture<ConfigurationTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ConfigurationTableComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConfigurationTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
