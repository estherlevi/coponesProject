import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-company-details',
  templateUrl: './company-details.component.html',
  styleUrls: ['./company-details.component.css']
})
export class CompanyDetailsComponent implements OnInit {

  @Input() company: any = { };
  @Output() onClose = new EventEmitter();
  saveCompany() {
    if (this.company.id) {
      this.update()
    }
    else {
      this.insert()
    }
  }
  deleteCompany() {
    if (this.company.id) {
      this.api.delete("admin/company", this.company.id)
        .subscribe(x => {
          this.onClose.emit(true);
        })
    }
  }
  insert() {
    this.api.post("admin/company/add", this.company)
      .subscribe(x => {
        this.onClose.emit(true);
      })
  }
  update() {
    this.api.post("admin/company/update", this.company)
      .subscribe(x => {
        this.onClose.emit(true);
      })
  }
  constructor(private api: ApiService) { }

  ngOnInit(): void {
  }

}
