import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CompanyComponent} from './component/company/company.component';
import {DashboardComponent} from './component/dashboard/dashboard.component';
import {CompanyDetailComponent} from './component/company-detail/company-detail.component';
import {CompanyAddComponent} from './component/company-add/company-add.component';

const routes: Routes = [
  {path: '', redirectTo: '/dashboard', pathMatch: 'full'},
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
