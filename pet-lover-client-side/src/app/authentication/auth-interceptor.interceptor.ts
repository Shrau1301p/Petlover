import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpResponse
} from '@angular/common/http';
import { catchError, Observable, tap } from 'rxjs';
import { ToastMessageService } from '../services/toastMessageService/toast-message.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private toastMsg:ToastMessageService) {}

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    
    if(localStorage.getItem("IsAuthenticated")){
      const token = localStorage.getItem("token"); 
      request = request.clone({
        setHeaders:{
          Authorization: `Bearer ${token}`
        }
      })
    }
    return next.handle(request).pipe(
      tap((event: HttpEvent<any>) =>{
        if (event instanceof HttpResponse) {
          if(event.body.message != null){
            this.toastMsg.handleHttpMessageToast(event.body.statusCode,event.body.message,event.body.error);
          }
        }
      }),
      catchError((error) => {
        throw error;
      })
    );
  }
}