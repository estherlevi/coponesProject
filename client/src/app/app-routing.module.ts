import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllCompaniesComponent } from './core/all-companies/all-companies.component';
import { AllCouponsViewComponent } from './core/all-coupons-view/all-coupons-view.component';
import { AllCouponsComponent } from './core/all-coupons/all-coupons.component';
import { AllCustomersComponent } from './core/all-customers/all-customers.component';
import { CompanyInfoComponent } from './core/company-info/company-info.component';
import { AdministratorPageComponent } from './pages/administrator-page/administrator-page.component';
import { CompanyPageComponent } from './pages/company-page/company-page.component';
import { CustomerPageComponent } from './pages/customer-page/customer-page.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { MainPageComponent } from './pages/main-page/main-page.component';
import { LoginService } from './services/login.service';

const routes: Routes = [

  {
    path: "main", component: MainPageComponent, children: [
      {
        path: "administrator-page", component: AdministratorPageComponent,
        children: [
          { path: "AllCompanies", component: AllCompaniesComponent },
          { path: "AllCustomers", component: AllCustomersComponent },
          { path: "", redirectTo: "AllCompanies", pathMatch: "full" },
          { path: "**", redirectTo: "AllCompanies", pathMatch: "full" },
        ]

      },
      {
        path: "company-page", component: CompanyPageComponent, children: [
          { path: "AllCoupons", component: AllCouponsComponent },
          { path: "CompanyInfo", component: CompanyInfoComponent },
          { path: "", redirectTo: "CompanyInfo", pathMatch: "full" },
          { path: "**", redirectTo: "CompanyInfo", pathMatch: "full" },
        ]
      },

      {
        path: "customer-page", component: CustomerPageComponent, children: [
          { path: "Coupons", component: AllCouponsViewComponent },
          { path: "", redirectTo: "Coupons", pathMatch: "full" },
          { path: "**", redirectTo: "Coupons", pathMatch: "full" },
        ]
      },
      { path: "", redirectTo: "customer-page", pathMatch: "full" },
      { path: "**", redirectTo: "", pathMatch: "full" },
    ]
  },
  { path: "login", component: LoginPageComponent },
  { path: "", redirectTo: "login", pathMatch: "full" },
  { path: "**", redirectTo: "", pathMatch: "full" },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  providers: [LoginService],
  exports: [RouterModule]
})
export class AppRoutingModule { }
