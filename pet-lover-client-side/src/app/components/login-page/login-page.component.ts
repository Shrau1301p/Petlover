import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Subject, takeUntil } from 'rxjs';
import { AuthenticationApiServiceService } from '../../services/authapiService/authentication-api-service.service';
import { ToastMessageService } from '../../services/toastMessageService/toast-message.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.scss'
})
export class LoginPageComponent implements OnInit, OnDestroy {
  private destroy$ = new Subject();
  loginForm! : FormGroup;

  constructor(private authservice:AuthenticationApiServiceService,private toastMsg: ToastMessageService, private routes:Router){}
  
  ngOnInit(): void {
    this.loginForm = new FormGroup({
      email : new FormControl(''),
      password : new FormControl('')
    })  
  }

  async login(){
    
    const data={
      username: this.loginForm.value.email,
      password : this.loginForm.value.password
    }
    this.authservice.login(data).pipe(takeUntil(this.destroy$)).subscribe({
      next: (respo:any)=> {
        if(respo.statusCode == 200){
          console.log("Token: ",respo.data);
          localStorage.setItem("IsAuthenticated","true");
          localStorage.setItem("token",respo.data);
          this.routes.navigate(['home']);
        } 
      },
      error: (error)=>{
        this.toastMsg.generateToast('error','something went wrong');
      }
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.complete();
  }
}
