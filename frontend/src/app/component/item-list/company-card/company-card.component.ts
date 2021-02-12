import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CompanyExtended} from "../../../dto/company-extended";

@Component({
  selector: 'app-company-card',
  templateUrl: './company-card.component.html',
  styleUrls: ['./company-card.component.scss']
})
export class CompanyCardComponent implements OnInit {

  @Input() company: CompanyExtended;
  @Output() xButtonClick: EventEmitter<any> = new EventEmitter<any>();
  @Output() cardClick: EventEmitter<any> = new EventEmitter<any>();
  isCompany = false;

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
