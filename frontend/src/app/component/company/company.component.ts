import {Component, OnInit} from '@angular/core';
import {CompanyService} from '../../service/company.service';
import {ActivatedRoute} from '@angular/router';
import {Location} from '@angular/common';
import {Company} from '../../dto/company';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.scss']
})
export class CompanyComponent implements OnInit {
  company: Company = {
    id: 1,
    name: 'AMD'
  };

  constructor(private companyService: CompanyService) {
  }

  testGetRequest(): void {
    this.companyService.getCompany().subscribe((i) => {
      console.log(i);
    });

  }

  ngOnInit(): void {
  }

}
