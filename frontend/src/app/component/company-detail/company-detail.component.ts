import {Component, OnInit, Input} from '@angular/core';
import {Company} from '../../dto/company';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
import {CompanyService} from '../../service/company.service';

@Component({
  selector: 'app-company-detail',
  templateUrl: './company-detail.component.html',
  styleUrls: ['./company-detail.component.scss']
})
export class CompanyDetailComponent implements OnInit {
  @Input() company: Company | undefined;

  constructor(private companyService: CompanyService,
              private route: ActivatedRoute,
              private location: Location) {

  }

  ngOnInit(): void {
    this.getCompany();
  }

  getCompany(): void {
    // @ts-ignore
    const id = +this.route.snapshot.paramMap.get('id');
    this.companyService.getCompanyById(id).subscribe(company => this.company = company);
  }

  goBack(): void {
    this.location.back();
  }

}
