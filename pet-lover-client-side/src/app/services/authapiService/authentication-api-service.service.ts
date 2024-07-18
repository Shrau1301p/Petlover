import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { responseMsg } from '../../constants/responseMsg';
import { environment } from '../../../environments/environment.development';
import { ENDPOINTS } from '../../enums/endpoints';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationApiServiceService {

  constructor(private http:HttpClient) { 
  }
  register(data:any): Observable<HttpResponse<responseMsg<any>>>{
    return this.http.post<HttpResponse<responseMsg<any>>>(`${environment.BASE_URL}${ENDPOINTS.REGISTER}`,data);
  }
}
