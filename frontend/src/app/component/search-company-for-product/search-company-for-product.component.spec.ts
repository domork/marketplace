import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchCompanyForProductComponent } from './search-company-for-product.component';

describe('SearchCompanyForProductComponent', () => {
  let component: SearchCompanyForProductComponent;
  let fixture: ComponentFixture<SearchCompanyForProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchCompanyForProductComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchCompanyForProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
