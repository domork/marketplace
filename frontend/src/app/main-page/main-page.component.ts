import {Component, OnInit} from '@angular/core';
import {Product} from '../dto/product';
import {ProductService} from '../service/product.service';
import {CompanyExtended} from '../dto/company-extended';

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

  constructor(private productService: ProductService) {
  }

  ngOnInit(): void {
  }

  addProduct(newProduct: Product): void {

    this.productService.addNewProduct(newProduct).subscribe(product => {
      console.log(product + ' was successfully added');
      this.currentProducts.push(product);
    });
    console.log(this.currentProducts);

  }

  showProductAddDiv(): void {
    this.showCompanyAdd = false;
    this.showProductAdd = true;
  }

  showCompanyAddDiv(): void {
    this.showCompanyAdd = true;
    this.showProductAdd = false;
  }

}
