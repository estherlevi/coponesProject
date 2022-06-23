import { Component, OnInit } from '@angular/core';
import { MenuItem } from 'primeng/api';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-company-page',
  templateUrl: './company-page.component.html',
  styleUrls: ['./company-page.component.css']
})
export class CompanyPageComponent implements OnInit {
  public items: MenuItem[];
  info: any;


  constructor(private api:ApiService) { 
    this.items = [
      {
        label: 'CompanyInfo', icon: 'pi pi-fw pi-file', routerLink: ['CompanyInfo'],
        routerLinkActiveOptions: '{ exact: true }'
      },
      {
        label: 'Coupons', icon: 'pi pi-fw pi-file', routerLink: ['AllCoupons'],
        routerLinkActiveOptions: '{ exact: true }'
      }

    ];
  }

  ngOnInit(): void {
   // this.getData();
  }


}
