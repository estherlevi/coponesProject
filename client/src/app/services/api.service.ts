import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { MessageService } from 'primeng/api';


@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private baseUrl: string = "http://localhost:8080/api";

  private errorMsg = (error: HttpErrorResponse) => {
    this.msg.add({ severity: 'error', summary: `${error.status}`, detail: error.error.message });
    return throwError(error);
  }

  public get(entity: string, id: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${entity}/${id}`, { headers: this.getHeaders() })
      .pipe(catchError(this.errorMsg))
  }
  public getAll(entity: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/${entity}`, { headers: this.getHeaders() })
    .pipe(catchError(this.errorMsg));
  }
  public post(entity: string, obj: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/${entity}`, obj, { headers: this.getHeaders() })
    .pipe(catchError(this.errorMsg));
  }
  public put(entity: string, obj: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/${entity}`, obj, { headers: this.getHeaders() })
    .pipe(catchError(this.errorMsg));
  }
  public delete(entity: string, id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/${entity}/${id}`, { headers: this.getHeaders() })
    .pipe(catchError(this.errorMsg));
  }

  private getHeaders(): any {
    const token = localStorage.getItem("token");

    const headers = new HttpHeaders({ "token": `${token}` })
    return headers;
  }

  constructor(private http: HttpClient, private msg: MessageService) { }
}
