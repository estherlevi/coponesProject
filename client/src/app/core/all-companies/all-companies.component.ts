import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-all-companies',
  templateUrl: './all-companies.component.html',
  styleUrls: ['./all-companies.component.css']
})
export class AllCompaniesComponent implements OnInit {

  public companies: Array<any> = [];
  public selectCompany: any;
  public openDialog = false;
  openNew() {
    this.openDialog = true;
    this.selectCompany = {};
  }
  editCompany(c: any) {
    this.openDialog = true;
    this.selectCompany = c;
  }
  hideDialog() {
    this.openDialog = false;
  }
  onClose(e:any) {
    this.openDialog = false;
    this.selectCompany = null;
    this.getData();
  }

  private getData(){
    this.api.getAll("admin/companies/all")
      .subscribe(x => {
        this.companies = x;
      })
  }
  constructor(private api: ApiService) { }

  ngOnInit(): void {
    this.getData();
  }


}
