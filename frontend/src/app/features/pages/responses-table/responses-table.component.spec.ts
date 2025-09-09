import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResponsesTableComponent } from './responses-table.component';

describe('ResponsesTableComponent', () => {
  let component: ResponsesTableComponent;
  let fixture: ComponentFixture<ResponsesTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ResponsesTableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ResponsesTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
