import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-all-customers',
  templateUrl: './all-customers.component.html',
  styleUrls: ['./all-customers.component.css']
})
export class AllCustomersComponent implements OnInit {
  public customers: Array<any> = [];
  public selectCustomer: any;
  public openDialog = false;
  openNew() {
    this.openDialog = true;
    this.selectCustomer = {};
  }
  editCompany(c: any) {
    this.openDialog = true;
    this.selectCustomer = c;
  }
  hideDialog() {
    this.openDialog = false;
  }
  onClose(e:any) {
    this.openDialog = false;
    this.selectCustomer = null;
    this.getData();
  }

  private getData(){
    this.api.getAll("admin/customers/all")
      .subscribe(x => {
        this.customers = x;
      })
  }
  constructor(private api: ApiService) { }

  ngOnInit(): void {
    this.getData();
  }

}
