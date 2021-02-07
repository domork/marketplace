import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CompanyComponent} from './component/company/company.component';
import {DashboardComponent} from './component/dashboard/dashboard.component';
import {CompanyDetailComponent} from './component/company-detail/company-detail.component';
import {CompanyAddComponent} from './component/company-add/company-add.component';
import {MainPageComponent} from './main-page/main-page.component';

const routes: Routes = [
  {path: '', component: MainPageComponent},
  {path: 'company', component: CompanyComponent},
  {path: 'dashboard', component: DashboardComponent},
  {path: 'company/:id', component: CompanyDetailComponent},
  {path: 'add/company', component: CompanyAddComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
