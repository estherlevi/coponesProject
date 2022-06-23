import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-coupon-details',
  templateUrl: './coupon-details.component.html',
  styleUrls: ['./coupon-details.component.css']
})
export class CouponDetailsComponent implements OnInit {

  public categoryOptions = ["","FOOD", "ELECTRONICS", "HOME", "CLOTHING", "CAMPING", "VACATION"];

  @Input() id: number | undefined;
  @Output() onClose = new EventEmitter();
  public coupon: any;
  save() {
    if (this.coupon.id) {
      this.update()
    }
    else {
      this.insert()
    }
  }
  deleteCoupon() {
    if (this.coupon.id) {
      this.api.delete("company", this.coupon.id)
        .subscribe(x => {
          this.onClose.emit(true);
        })
    }
  }
  update() {
    this.api.post("company/updateCoupon", this.coupon)
      .subscribe(x => {
        this.onClose.emit(true);
      })
  }
  insert() {
    this.api.post("company/addCoupon", this.coupon)
      .subscribe(x => {
        this.onClose.emit(true);
      })
  }
  imageUplode(event: any) {
    const file = event.target.files[0];
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => {
      this.coupon.image = reader.result;
    };
  }
  constructor(private api: ApiService) { }

  ngOnInit(): void {
    if (this.id) {
      this.api.get("company", this.id)
        .subscribe(x => {
          this.coupon = x;
        })
    }
    else{
      this.coupon = {};
    }

  }

}
