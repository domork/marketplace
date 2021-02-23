import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Product} from '../../../dto/product';
import {EditProductComponent} from '../../edit-product/edit-product.component';
import {MatDialog} from '@angular/material/dialog';
import {ProductService} from '../../../service/product.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {

  fetchingProducts = true;
  successfullyFetchedProducts = false;
  errorMessage = '';
  errorMessageIsActive = false;

  constructor(public dialog: MatDialog, private productService: ProductService) {
  }

  @Output() deleteProduct: EventEmitter<Product> = new EventEmitter<Product>();
  @Input() productList: Product[] = [];

  ngOnInit(): void {
    this.getProducts();
  }

  getProducts(): void {
    this.productService.getProducts().subscribe(products => {
      this.productList = products;
      this.fetchingProducts = false;
      this.successfullyFetchedProducts = true;
    }, err => {

      this.errorMessage = err.error.error;
      switch (this.errorMessage) {
        case 'Not Found': {
          this.errorMessage = 'There are no products saved in the DB right now. Please, add some clicking on the left top logo';
          break;
        }
        case 'Unauthorized': {
          this.errorMessage = 'You are not authorized! Please login first';
          break;
        }
      }
      this.fetchingProducts = false;
      this.successfullyFetchedProducts = false;
    });

  }


  onProductDeleteButtonClicked(item: Product): void {
    const index = this.productList.indexOf(item);
    this.productService.deleteProduct(item).subscribe(_ => {
      console.log(`delete product ID ${item.id} succeeded`);
      this.productList.splice(index, 1);
    }, error => {
      this.errorMessage = 'Only admins can delete the product!';
      this.errorMessageIsActive = true;

    });
  }

  errorMessageDisable(): void {
    this.errorMessageIsActive = false;
  }

  onProductCardClicked(item: Product): void {
    const dialogRef = this.dialog.open(EditProductComponent, {
      width: '580px',
      data: item
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {

        this.productService.updateProduct(result, item.id).subscribe(prod => {
          console.log(`success at updating the product with id ${prod.id}`);
          this.productList[this.productList.indexOf(item)] = result;
        }, error => {
          this.errorMessage = 'Only admins can edit the product!';
          this.errorMessageIsActive = true;

        });
      }
    });
  }
}
