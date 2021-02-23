import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CompanyService} from '../../../service/company.service';
import {MessageService} from '../../../service/message.service';
import {CompanyExtended} from '../../../dto/company-extended';
import {EditCompanyComponent} from '../../edit-company/edit-company.component';
import {MatDialog} from '@angular/material/dialog';
import {Country} from '@angular-material-extensions/select-country';

@Component({
  selector: 'app-company',
  templateUrl: './company.component.html',
  styleUrls: ['./company.component.scss']
})
export class CompanyComponent implements OnInit {
  fetchingCompanies = true;
  successfullyFetchedCompanies = false;
  errorMessage = '';
  errorMessageIsActive = false;

  @Input() companyList: CompanyExtended[] = [];
  @Output() deleteCompany: EventEmitter<CompanyExtended> = new EventEmitter<CompanyExtended>();

  constructor(private companyService: CompanyService, public dialog: MatDialog) {
  }

  getCompanies(): void {
    this.companyService.getCompanies().subscribe(companies => {
      this.companyList = companies;
      this.fetchingCompanies = false;
      this.successfullyFetchedCompanies = true;

    }, err => {
      this.errorMessage = err.error.error;
      switch (this.errorMessage) {
        case 'Not Found': {
          this.errorMessage = 'There are no companies saved in the DB right now. Please, add some clicking on the left top logo';
          break;
        }
        case 'Unauthorized': {
          this.errorMessage = 'You are not authorized! Please login first';
          break;
        }
      }
      this.fetchingCompanies = false;
      this.successfullyFetchedCompanies = false;
    });

  }


  ngOnInit(): void {
    this.getCompanies();
  }

  onCompanyDeleteButtonClicked(item: CompanyExtended): void {
    const index = this.companyList.indexOf(item);
    this.companyService.deleteCompany(item).subscribe(a => {
      console.log(`delete ID ${item.id} succeeded`);

      this.companyList.splice(index, 1);
    }, error => {
      this.errorMessage = 'Only admins can delete the company!';
      this.errorMessageIsActive = true;
    });
  }

  onCompanyCardClicked(item: CompanyExtended): void {
    if (item.basedIn) {
      item.basedIn = (item.basedIn as Country).name;
    }
    const dialogRef = this.dialog.open(EditCompanyComponent, {
      width: '580px',
      data: item
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.companyService.updateCompany(result, item.id).subscribe(s => {
          console.log(`success at updating the company with id ${result.id}`);
          this.companyList[this.companyList.indexOf(item)] = result;
        }, error => {
          this.errorMessage = 'Only admins can edit the company!';
          this.errorMessageIsActive = true;

        });
      }
    });
  }


  errorMessageDisable(): void {
    this.errorMessageIsActive = false;
  }
}
