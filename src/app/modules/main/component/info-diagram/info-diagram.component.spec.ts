import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InfoDiagramComponent } from './info-diagram.component';

describe('InfoDiagramComponent', () => {
  let component: InfoDiagramComponent;
  let fixture: ComponentFixture<InfoDiagramComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [InfoDiagramComponent],
    }).compileComponents();

    fixture = TestBed.createComponent(InfoDiagramComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
