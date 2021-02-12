import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Product} from '../../../dto/product';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.scss']
})
export class ProductCardComponent implements OnInit {

  @Input() product: Product;
  @Output() xButtonClick: EventEmitter<any> = new EventEmitter<any>();
  @Output() cardClick: EventEmitter<any> = new EventEmitter<any>();
  isProduct = false;

  constructor() {
  }

  ngOnInit(): void {
  }

  onXButtonClick(): void {
    this.xButtonClick.emit();
  }

  onCardClick(): void {
    this.cardClick.emit();
  }
}
