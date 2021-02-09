import {Component, OnInit} from '@angular/core';
import {Company} from '../../dto/company';
import {CompanyService} from '../../service/company.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  companies: Company[] = [];

  constructor(private companyService: CompanyService) {
  }

  ngOnInit(): void {
    this.getCompanies();
  }

  getCompanies(): void {
    this.companyService.getCompanies().subscribe(companies => this.companies = companies);
    // this.companyService.getCompany().subscribe(companies => this.companies = companies.slice(0, 4));
  }
}
