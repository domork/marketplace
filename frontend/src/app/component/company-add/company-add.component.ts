import {Component, OnInit} from '@angular/core';
import {CompanyExtended} from '../../dto/company-extended';
import {CompanyService} from '../../service/company.service';


@Component({
  selector: 'app-company-add',
  templateUrl: './company-add.component.html',
  styleUrls: ['./company-add.component.scss']
})
export class CompanyAddComponent implements OnInit {

  model = new CompanyExtended
  (undefined, 'AMD', 'www.amd.com', 'gud company', undefined);
  submitted = false;

  onSubmit(): void {
    this.submitted = true;
  }

  add(): void {
    this.companyService.addNewCompany(this.model).
    subscribe(company => console.log(company + ' was successfully added'));

  }

  constructor(private companyService: CompanyService) {
  }

  ngOnInit(): void {
  }


}
