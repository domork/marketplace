import {Component, OnInit} from '@angular/core';
import {Observable, Subject} from 'rxjs';
import {CompanyExtended} from '../../dto/company-extended';
import {CompanyService} from '../../service/company.service';
import {debounceTime, distinctUntilChanged, switchMap} from 'rxjs/operators';

@Component({
  selector: 'app-search-company-for-product',
  templateUrl: './search-company-for-product.component.html',
  styleUrls: ['./search-company-for-product.component.scss']
})
export class SearchCompanyForProductComponent implements OnInit {
  searchbarIsActive = false;
  searchbarValue: string;
  companies$: Observable<CompanyExtended[]>;
  private searchTerms = new Subject<string>();

  constructor(private companyService: CompanyService) {
  }

  search(term: string): void {
    this.searchTerms.next(term);
  }

  ngOnInit(): void {
    this.companies$ = this.searchTerms.pipe(
      debounceTime(300),
      distinctUntilChanged(),
      switchMap((term: string) => this.companyService.getCompaniesByName(term))
    );
  }


}
