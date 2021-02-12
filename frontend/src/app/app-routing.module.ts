import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CompanyComponent} from './component/company/company.component';
import {DashboardComponent} from './component/dashboard/dashboard.component';
import {CompanyDetailComponent} from './component/company-detail/company-detail.component';
import {CompanyAddComponent} from './component/company-add/company-add.component';
import {MainPageComponent} from './main-page/main-page.component';
import {ProductComponent} from "./component/product/product.component";
import {AboutComponent} from "./component/about/about.component";
import {ContactComponent} from "./component/contact/contact.component";
import {BugsComponent} from "./component/bugs/bugs.component";

const routes: Routes = [
  {path: '', component: MainPageComponent},
  {path: 'companies', component: CompanyComponent},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'company/:id', component: CompanyDetailComponent},
  {path: 'add/company', component: CompanyAddComponent},
  {path: 'products', component: ProductComponent},
  {path: 'about', component: AboutComponent},
  {path: 'contact', component: ContactComponent},
  {path: 'additionalStuff', component: BugsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
