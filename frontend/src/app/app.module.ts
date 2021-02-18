import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {CompanyComponent} from './component/pages/company/company.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {CompanyDetailComponent} from './component/temp/company-detail/company-detail.component';
import {MessagesComponent} from './component/temp/messages/messages.component';
import {DashboardComponent} from './component/temp/dashboard/dashboard.component';
import {CompanyAddComponent} from './component/temp/company-add/company-add.component';
import {MainPageComponent} from './main-page/main-page.component';
import {ItemListComponent} from './component/item-list/item-list.component';
import {AddCompanyFormComponent} from './component/add-company-form/add-company-form.component';
import {AddProductFormComponent} from './component/add-product-form/add-product-form.component';
import {ProductCardComponent} from './component/item-list/product-card/product-card.component';
import {CompanyCardComponent} from './component/item-list/company-card/company-card.component';
import {EditProductComponent} from './component/edit-product/edit-product.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatDialogModule} from '@angular/material/dialog';
import { EditCompanyComponent } from './component/edit-company/edit-company.component';
import { MatSelectCountryModule } from '@angular-material-extensions/select-country';
import { ProductComponent } from './component/pages/product/product.component';
import { AboutComponent } from './component/pages/about/about.component';
import { ContactComponent } from './component/pages/contact/contact.component';
import { BugsComponent } from './component/pages/bugs/bugs.component';
import { SearchCompanyForProductComponent } from './component/search-company-for-product/search-company-for-product.component';

@NgModule({
  declarations: [
    AppComponent,
    CompanyComponent,
    CompanyDetailComponent,
    MessagesComponent,
    DashboardComponent,
    CompanyAddComponent,
    MainPageComponent,
    ItemListComponent,
    AddCompanyFormComponent,
    AddProductFormComponent,
    ProductCardComponent,
    CompanyCardComponent,
    EditProductComponent,
    EditCompanyComponent,
    ProductComponent,
    AboutComponent,
    ContactComponent,
    BugsComponent,
    SearchCompanyForProductComponent
  ],
  imports: [
    MatSelectCountryModule.forRoot('en'),
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    BrowserAnimationsModule,
    MatDialogModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
