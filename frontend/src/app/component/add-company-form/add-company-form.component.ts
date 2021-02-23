import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {NgForm} from '@angular/forms';
import {CompanyExtended} from '../../dto/company-extended';
import {Country} from '@angular-material-extensions/select-country';

@Component({
  selector: 'app-add-company-form',
  templateUrl: './add-company-form.component.html',
  styleUrls: ['./add-company-form.component.scss']
})
export class AddCompanyFormComponent implements OnInit {

  @Input() item: CompanyExtended;
  @Output() formSubmit: EventEmitter<CompanyExtended> = new EventEmitter<CompanyExtended>();

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
      this.item = new CompanyExtended(undefined, '', undefined, undefined, undefined);
    }
  }

  onSubmit(form: NgForm): void {
    this.formSubmit.emit(form.value);
    form.reset();
    this.item.basedIn = '';
  }

  newCountry(): boolean {
    return this.isNewItem;
  }
}
