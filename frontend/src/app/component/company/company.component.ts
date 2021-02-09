import {Component, OnInit} from '@angular/core';
import {CompanyService} from '../../service/company.service';
import {Company} from '../../dto/company';
import {MessageService} from '../../service/message.service';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.scss']
})
export class CompanyComponent implements OnInit {
  companies: Company[] = [];

  constructor(private companyService: CompanyService, private messageService: MessageService) {
  }

  getCompanies(): void {
    this.companyService.getCompanies().subscribe(companies => this.companies = companies);
  }

  ngOnInit(): void {
    this.getCompanies();
  }

}
