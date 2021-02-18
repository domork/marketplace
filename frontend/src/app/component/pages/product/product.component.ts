import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Product} from "../../../dto/product";
import {EditProductComponent} from "../../edit-product/edit-product.component";
import {MatDialog} from "@angular/material/dialog";
import {ProductService} from "../../../service/product.service";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {

  constructor(public dialog: MatDialog, private productService: ProductService) {
  }

  @Output() deleteProduct: EventEmitter<Product> = new EventEmitter<Product>();
  @Input() productList: Product[] = [];

  ngOnInit(): void {
    this.getProducts();
  }

  getProducts(): void {
    this.productService.getProducts().subscribe(products => this.productList = products);
  }

  onProductDeleteButtonClicked(item: Product): void {
    let index = this.productList.indexOf(item);
    this.productService.deleteProduct(item).subscribe(_ => {
      console.log(`delete product ID ${item.id} succeeded`);
      this.productList.splice(index, 1);
    });
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
        });
      }
    });
  }
}
