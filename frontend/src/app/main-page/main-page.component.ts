import {Component, OnInit} from '@angular/core';
import {Product} from '../dto/product';
import {ProductService} from '../service/product.service';
import {CompanyExtended} from '../dto/company-extended';
import {CompanyService} from "../service/company.service";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.scss']
})
export class MainPageComponent implements OnInit {

  currentProducts: Product[] = new Array<Product>();
  currentCompanies: CompanyExtended[] = new Array<CompanyExtended>();

  showProductAdd = false;
  showCompanyAdd = false;

  constructor(private productService: ProductService,
              private companyService: CompanyService) {
  }

  ngOnInit(): void {
  }

  addProduct(newProduct: Product): void {

    this.productService.addNewProduct(newProduct).subscribe(product => {
      console.log(product + ' was successfully added');
      this.currentProducts.push(product);
    });
  }

  addCompany(newCompany: CompanyExtended): void {

    this.companyService.addNewCompany(newCompany).subscribe(company => {
      console.log(company + ' was successfully added');
      this.currentCompanies.push(company);
    });
  }

  showProductAddDiv(): void {
    this.showCompanyAdd = false;
    this.showProductAdd = true;
  }

  showCompanyAddDiv(): void {
    this.showCompanyAdd = true;
    this.showProductAdd = false;
  }

  deleteProduct(item: Product): void {
    let index = this.currentProducts.indexOf(item);
    this.productService.deleteProduct(item).subscribe(product => {
      console.log(`delete ID ${product.id} succeeded`);
      this.currentProducts.splice(index, 1);
    });

  }
}
