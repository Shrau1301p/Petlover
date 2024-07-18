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
    request = request.clone({
      setHeaders:{
        Authorization: 'Basic c2hyYXZhbmk6c2hyYXZhbmkxMjM='
      }
    })
    return next.handle(request).pipe(
      tap((event: HttpEvent<any>) =>{
        if (event instanceof HttpResponse) {
          this.toastMsg.handleHttpMessageToast(event.body.statusCode,event.body.message);
        }
      }),
      catchError((error) => {
        throw error;
      })
    );
  }
}