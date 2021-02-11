import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Product} from "../../dto/product";
import {CompanyExtended} from "../../dto/company-extended";

@Component({
  selector: 'app-edit-company',
  templateUrl: './edit-company.component.html',
  styleUrls: ['./edit-company.component.scss']
})
export class EditCompanyComponent implements OnInit {

  constructor(
    public dialogRefL: MatDialogRef<EditCompanyComponent>,
    @Inject(MAT_DIALOG_DATA) public item: CompanyExtended
  ) {
  }

  ngOnInit(): void {
  }

  onSubmitted(updatedItem: CompanyExtended): void {
    this.dialogRefL.close(updatedItem);
  }
}
