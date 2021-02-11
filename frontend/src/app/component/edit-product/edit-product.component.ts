import {Component, Inject, Input, OnInit} from '@angular/core';
import {Product} from '../../dto/product';
import {MAT_DIALOG_DATA, MatDialog, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-edit-product',
  templateUrl: './edit-product.component.html',
  styleUrls: ['./edit-product.component.scss']
})
export class EditProductComponent implements OnInit {

  constructor(
    public dialogRefL: MatDialogRef<EditProductComponent>,
    @Inject(MAT_DIALOG_DATA) public item: Product
  ) {
  }

  ngOnInit(): void {
  }

  onSubmitted(updatedItem: Product): void {
  this.dialogRefL.close(updatedItem);
  }
}
