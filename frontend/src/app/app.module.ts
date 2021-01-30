import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {CompanyComponent} from './component/company/company.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {CompanyDetailComponent} from './component/company-detail/company-detail.component';
import {MessagesComponent} from './component/messages/messages.component';
import {DashboardComponent} from './component/dashboard/dashboard.component';
import {CompanyAddComponent} from './component/company-add/company-add.component';

@NgModule({
  declarations: [
    AppComponent,
    CompanyComponent,
    CompanyDetailComponent,
    MessagesComponent,
    DashboardComponent,
    CompanyAddComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
