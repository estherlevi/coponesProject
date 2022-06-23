import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-company-info',
  templateUrl: './company-info.component.html',
  styleUrls: ['./company-info.component.css']
})
export class CompanyInfoComponent implements OnInit {
  info: any;

  constructor(private api: ApiService) { }

  ngOnInit(): void {
    this.getData();
  }
  getData() {
    this.api.getAll("company/companyInfo")
      .subscribe(x => {
        this.info = x;
      })
  }
}
