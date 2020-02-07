import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ManagerestaurantComponent } from './managerestaurant.component';

describe('ManagerestaurantComponent', () => {
  let component: ManagerestaurantComponent;
  let fixture: ComponentFixture<ManagerestaurantComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ManagerestaurantComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ManagerestaurantComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
