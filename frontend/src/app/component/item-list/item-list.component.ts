import {Component, Input, OnInit} from '@angular/core';
import {Product} from "../../dto/product";
import {CompanyExtended} from "../../dto/company-extended";

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.scss']
})
export class ItemListComponent implements OnInit {

  @Input() productList: Product[];
  @Input() companyList: CompanyExtended[];

  constructor() {
  }

  ngOnInit(): void {
  }

}
