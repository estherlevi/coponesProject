import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-all-coupons',
  templateUrl: './all-coupons.component.html',
  styleUrls: ['./all-coupons.component.css']
})
export class AllCouponsComponent implements OnInit {

public coupons:Array<any> = [];
public selectCoupon: any;
  public openDialog = false;
  openNew() {
    this.openDialog = true;
    this.selectCoupon = {};
  }
  editCoupon(c: any) {
    this.openDialog = true;
    this.selectCoupon = c;
  }
  hideDialog(e:any) {
    this.onClose(e);
  }
  onClose(e:any) {
    this.openDialog = false;
    this.selectCoupon = null;
    this.getData();
  }

  private getData(){
    this.api.getAll("company/allCoupons")
      .subscribe(x => {
        this.coupons = x;
      })
  }
  constructor(private api: ApiService) { }

  ngOnInit(): void {
    this.getData();
  }

}
