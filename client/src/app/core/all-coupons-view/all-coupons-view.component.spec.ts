import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllCouponsViewComponent } from './all-coupons-view.component';

describe('AllCouponsViewComponent', () => {
  let component: AllCouponsViewComponent;
  let fixture: ComponentFixture<AllCouponsViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AllCouponsViewComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AllCouponsViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
