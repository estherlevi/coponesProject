import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-customer-page',
  templateUrl: './customer-page.component.html',
  styleUrls: ['./customer-page.component.css']
})
export class CustomerPageComponent implements OnInit {

  public items: MenuItem[];


  constructor() { 
    this.items = [
      // {
      //   label: 'CompanyInfo', icon: 'pi pi-fw pi-file', routerLink: ['CompanyInfo'],
      //   routerLinkActiveOptions: '{ exact: true }'
      // },
      {
        label: 'Coupons', icon: 'pi pi-fw pi-file', routerLink: ['Coupons'],
        routerLinkActiveOptions: '{ exact: true }'
      }

    ];
  }

  ngOnInit(): void {
  }

}
