import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SignupaddressComponent } from './signupaddress.component';

describe('SignupaddressComponent', () => {
  let component: SignupaddressComponent;
  let fixture: ComponentFixture<SignupaddressComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SignupaddressComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SignupaddressComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
