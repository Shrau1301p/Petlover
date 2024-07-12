import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationApiServiceService {

  constructor(private http:HttpClient) { 
  }
  register(data:any): Observable<HttpResponse<any>>{
    return this.http.post<HttpResponse<any>>('http://localhost:8080/register',data);
  }
}
