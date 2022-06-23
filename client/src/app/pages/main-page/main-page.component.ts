import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.css']
})
export class MainPageComponent implements OnInit {

  public userType: string | null = null;

  public logOut() {
    this.loginService.logOut()
      .subscribe(x => {
        this.router.navigate(['..', 'login']);
      })

  }



  constructor(private loginService: LoginService, private router: Router) {
  }

  ngOnInit(): void {
    if (!this.loginService.isLogin()) {
      this.router.navigate(['/', 'login']);
    }
    else {
      this.userType = localStorage.getItem("loginType");
      if (this.userType) {
        switch (this.userType) {
          case "ADMINISTRATOR":
            this.router.navigate(['main', 'administrator-page']);
            break;
          case "COMPANY":
            this.router.navigate(['main', 'company-page']);
            break;
          case "CUSTOMER":
            this.router.navigate(['main', 'customer-page']);
            break;

          default:
            this.router.navigate(['/', 'login']);
            break;
        }
      }
    }
  }

}
