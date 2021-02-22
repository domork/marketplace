import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CompanyComponent} from './component/pages/company/company.component';
import {DashboardComponent} from './component/temp/dashboard/dashboard.component';
import {CompanyDetailComponent} from './component/temp/company-detail/company-detail.component';
import {CompanyAddComponent} from './component/temp/company-add/company-add.component';
import {MainPageComponent} from './main-page/main-page.component';
import {ProductComponent} from './component/pages/product/product.component';
import {AboutComponent} from './component/pages/about/about.component';
import {ContactComponent} from './component/pages/contact/contact.component';
import {BugsComponent} from './component/pages/bugs/bugs.component';
import {LoginComponent} from './component/pages/login/login.component';
import {RegisterComponent} from './component/pages/register/register.component';

const routes: Routes = [
  {path: '', component: MainPageComponent},
  {path: 'companies', component: CompanyComponent},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'company/:id', component: CompanyDetailComponent},
  {path: 'add/company', component: CompanyAddComponent},
  {path: 'products', component: ProductComponent},
  {path: 'about', component: AboutComponent},
  {path: 'contact', component: ContactComponent},
  {path: 'additionalStuff', component: BugsComponent},
  {path: 'login', component: LoginComponent},
  {path: 'signup', component: RegisterComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
