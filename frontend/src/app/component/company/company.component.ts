import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CompanyService} from '../../service/company.service';
import {MessageService} from '../../service/message.service';
import {CompanyExtended} from "../../dto/company-extended";
import {EditCompanyComponent} from "../edit-company/edit-company.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.scss']
})
export class CompanyComponent implements OnInit {

  @Input() companyList: CompanyExtended[] = [];
  @Output() deleteCompany: EventEmitter<CompanyExtended> = new EventEmitter<CompanyExtended>();

  constructor(private companyService: CompanyService, public dialog: MatDialog) {
  }

  getCompanies(): void {
    this.companyService.getCompanies().subscribe(companies => this.companyList = companies);
  }

  ngOnInit(): void {
    this.getCompanies();
  }

  onCompanyDeleteButtonClicked(item: CompanyExtended): void {
    let index = this.companyList.indexOf(item);
    this.companyService.deleteCompany(item).subscribe(_ => {
      console.log(`delete ID ${item.id} succeeded`);
      this.companyList.splice(index, 1);
    });
  }

  onCompanyCardClicked(item: CompanyExtended): void {
    const dialogRef = this.dialog.open(EditCompanyComponent, {
      width: '580px',
      data: item
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.companyService.updateCompany(result, item.id).subscribe(s => {
          console.log(`success at updating the company with id ${result.id}`);
          this.companyList[this.companyList.indexOf(item)] = result;
        });
      }
    });
  }
}
