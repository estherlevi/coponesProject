import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {

  public type: any;
  public email: string;
  public password: string;
  public typeOptions: Array<any>;

  public login() {
    this.loginService.login(this.type, this.email, this.password)
      .subscribe(x => {
        if (x) {
          this.router.navigate(['/', 'main']);
        }
        else {
          this.type = "";
          this.email = "";
          this.password = "";
        }
      })
  }



  constructor(private loginService: LoginService, private router: Router) {
    this.type = "";
    this.email = "";
    this.password = "";
    this.typeOptions = ["ADMINISTRATOR", "COMPANY", "CUSTOMER"];
  }

  ngOnInit(): void {
    if (this.loginService.isLogin()) {
      this.router.navigate(['/', 'main']);
    }
  }

}
