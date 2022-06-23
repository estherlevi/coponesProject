import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { observable, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private baseUrl: string = "http://localhost:8080";
  private headers: any = new HttpHeaders().set(
    'Content-Type', 'application/x-www-form-urlencoded'
  )

  public isLogin(): boolean {
    return localStorage.getItem("token") ? true : false;
  }
  public login(type: string, email: string, pasword: string): Observable<any> {
    return new Observable<any>((res) => {
      let body = `email=${email}&password=${pasword}&clientType=${type}`;
      this.http.post(`${this.baseUrl}/login`, body, { headers: this.headers, responseType:"text"})
        .subscribe(x => {
          if(x){
            localStorage.setItem("token", x);
            localStorage.setItem("loginType", type);
          }
          return res.next(true);
        })

    })
  }
  public logOut(): Observable<any> {
    return new Observable<any>((res) => {
      //todo
      localStorage.removeItem("token");
      localStorage.removeItem("loginType");
      return res.next(true);
    })


  }




  constructor(private http: HttpClient) { }



}
