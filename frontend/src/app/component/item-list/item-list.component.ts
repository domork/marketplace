import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Product} from '../../dto/product';
import {CompanyExtended} from '../../dto/company-extended';
import {MatDialog} from '@angular/material/dialog';
import {EditProductComponent} from '../edit-product/edit-product.component';
import {ProductService} from '../../service/product.service';
import {CompanyService} from '../../service/company.service';
import {EditCompanyComponent} from "../edit-company/edit-company.component";

@Component({
    selector: 'app-item-list',
    templateUrl: './item-list.component.html',
    styleUrls: ['./item-list.component.scss']
})
export class ItemListComponent implements OnInit {

    @Input() productList: Product[] = [
        new Product(42, 'test',
            undefined, undefined,
            undefined, undefined, undefined)];
    @Input() companyList: CompanyExtended[];
    @Output() deleteProduct: EventEmitter<Product> = new EventEmitter<Product>();
    @Output() deleteCompany: EventEmitter<CompanyExtended> = new EventEmitter<CompanyExtended>();


    constructor(public dialog: MatDialog, private productService: ProductService,
                private companyService: CompanyService) {
    }

    ngOnInit(): void {
    }

    onProductDeleteButtonClicked(item: Product): void {
        this.deleteProduct.emit(item);
    }

    onCompanyDeleteButtonClicked(item: CompanyExtended): void {
        this.deleteCompany.emit(item);
    }

    onCompanyCardClicked(item: CompanyExtended): void {
        const dialogRef = this.dialog.open(EditCompanyComponent, {
            width: '580px',
            data: item
        });

        dialogRef.afterClosed().subscribe(result => {
            if (result) {
                this.companyService.updateCompany(result, item.id).subscribe(comp => {
                    console.log(`success at updating the company with id ${comp.id}`);
                    this.companyList[this.companyList.indexOf(item)] = result;
                });
            }
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
