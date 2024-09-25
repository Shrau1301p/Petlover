import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { AuthenticationApiServiceService } from '../../services/authapiService/authentication-api-service.service';
import { ToastMessageService } from '../../services/toastMessageService/toast-message.service';
import { Router } from '@angular/router';
import { Subject, takeUntil } from 'rxjs';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrl: './register-page.component.scss'
})
export class RegisterPageComponent implements OnInit, OnDestroy{
  private destroy$ = new Subject();
  signForm!: FormGroup;

  constructor(private authservice:AuthenticationApiServiceService,private toastMsg: ToastMessageService, private routes:Router){
  }

  ngOnInit(): void {
    this.signForm = new FormGroup({
      email : new FormControl(''),
      password : new FormControl(''),
      confirm : new FormControl('')
    })  
  }

  async getData(){
    const data={
      username: this.signForm.value.email,
      password : this.signForm.value.password
    }
    this.authservice.register(data).pipe(takeUntil(this.destroy$)).subscribe({
      next: (respo:any)=> {
        if(respo.statusCode == 200){
          localStorage.setItem("isFirstTimeLogin","true");
          this.routes.navigate(['login']);
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
