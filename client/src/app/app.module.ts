import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { CardModule } from 'primeng/card';
import { ButtonModule } from 'primeng/button';
import {DropdownModule} from 'primeng/dropdown';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {InputTextModule} from 'primeng/inputtext';
import {ToolbarModule} from 'primeng/toolbar';
import {TabMenuModule} from 'primeng/tabmenu';
import {TableModule} from 'primeng/table';
import {DialogModule} from 'primeng/dialog';
import {DataViewModule} from 'primeng/dataview';
import {MessagesModule} from 'primeng/messages';



import { MainPageComponent } from './pages/main-page/main-page.component';
import { CustomerPageComponent } from './pages/customer-page/customer-page.component';
import { CompanyPageComponent } from './pages/company-page/company-page.component';
import { AdministratorPageComponent } from './pages/administrator-page/administrator-page.component';
import { AllCompaniesComponent } from './core/all-companies/all-companies.component';
import { ApiService } from './services/api.service';
import { CompanyDetailsComponent } from './core/company-details/company-details.component';
import { AllCustomersComponent } from './core/all-customers/all-customers.component';
import { CustomerDetailsComponent } from './core/customer-details/customer-details.component';
import { CompanyInfoComponent } from './core/company-info/company-info.component';
import { AllCouponsComponent } from './core/all-coupons/all-coupons.component';
import { CouponDetailsComponent } from './core/coupon-details/coupon-details.component';
import { AllCouponsViewComponent } from './core/all-coupons-view/all-coupons-view.component';
import { MessageService } from 'primeng/api';
import {ToastModule} from 'primeng/toast';



@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    MainPageComponent,
    CustomerPageComponent,
    CompanyPageComponent,
    AdministratorPageComponent,
    AllCompaniesComponent,
    CompanyDetailsComponent,
    AllCustomersComponent,
    CustomerDetailsComponent,
    CompanyInfoComponent,
    AllCouponsComponent,
    CouponDetailsComponent,
    AllCouponsViewComponent
  ],
  imports: [
    BrowserModule,BrowserAnimationsModule,HttpClientModule,
    AppRoutingModule,FormsModule,
    CardModule,ButtonModule,DropdownModule,InputTextModule,ToolbarModule,TabMenuModule,TableModule,DialogModule,DataViewModule,MessagesModule,ToastModule
  ],
  providers: [ApiService,MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
