import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';

@Component({
  selector: 'app-administrator-page',
  templateUrl: './administrator-page.component.html',
  styleUrls: ['./administrator-page.component.css']
})
export class AdministratorPageComponent implements OnInit {

  public items: MenuItem[];
  constructor() {

    this.items = [
      {
        label: 'Companies', icon: 'pi pi-fw pi-file', routerLink: ['AllCompanies'],
        routerLinkActiveOptions: '{ exact: true }'
      },
      {
        label: 'Customers', icon: 'pi pi-fw pi-file', routerLink: ['AllCustomers'],
        routerLinkActiveOptions: '{ exact: true }'
      }
    ];
  }

  ngOnInit(): void {

  }

}
