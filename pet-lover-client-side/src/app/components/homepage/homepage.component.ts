import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject, takeUntil } from 'rxjs';
import { AuthenticationApiServiceService } from '../../services/authapiService/authentication-api-service.service';
import { ToastMessageService } from '../../services/toastMessageService/toast-message.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-homepage',
  templateUrl: './homepage.component.html',
  styleUrl: './homepage.component.scss'
})
export class HomepageComponent implements OnInit, OnDestroy{
  private destroy$ = new Subject();
  constructor(private authservice:AuthenticationApiServiceService,private toastMsg: ToastMessageService, private routes:Router){}
  ngOnInit(): void {
    
  }
  async auth(){
    
    this.authservice.auth().pipe(takeUntil(this.destroy$)).subscribe({
      next: (respo:any)=> {
        
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
