import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.css']
})
export class CustomerDetailsComponent implements OnInit {
  @Input() customer: any = { };
  @Output() onClose = new EventEmitter();
  saveCustomer() {
    if (this.customer.id) {
      this.update()
    }
    else {
      this.insert()
    }
  }
  deleteCustomer() {
    if (this.customer.id) {
      this.api.delete("admin/customer", this.customer.id)
        .subscribe(x => {
          this.onClose.emit(true);
        })
    }
  }
  insert() {
    this.api.post("admin/customer/add", this.customer)
      .subscribe(x => {
        this.onClose.emit(true);
      })
  }
  update() {
    this.api.post("admin/customer/update", this.customer)
      .subscribe(x => {
        this.onClose.emit(true);
      })
  }
  constructor(private api: ApiService) { }

  ngOnInit(): void {
  }

}
