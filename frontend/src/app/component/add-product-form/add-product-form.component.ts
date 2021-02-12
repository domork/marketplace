import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CompanyExtended} from '../../dto/company-extended';
import {NgForm} from '@angular/forms';
import {Product} from '../../dto/product';

@Component({
  selector: 'app-add-product-form',
  templateUrl: './add-product-form.component.html',
  styleUrls: ['./add-product-form.component.scss']
})
export class AddProductFormComponent implements OnInit {
  @Input() item: Product;
  @Output() formSubmit: EventEmitter<Product> = new EventEmitter<Product>();

  isNewItem = false;


  constructor() {
  }

  ngOnInit(): void {
    // if item has a value
    if (this.item) {
      // this means that an existing item object was passed into this component
      // therefore this is not a new item
      this.isNewItem = false;
    } else {
      this.isNewItem = true;
      this.item = new Product(undefined, '', undefined, undefined, '', undefined, 'new');
    }
  }

  onSubmit(form: NgForm): void {

    this.formSubmit.emit(form.value);
    form.reset();

  }

}
