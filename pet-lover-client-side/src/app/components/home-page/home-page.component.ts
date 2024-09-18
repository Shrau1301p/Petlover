import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject, takeUntil } from 'rxjs';
import { AuthenticationApiServiceService } from '../../services/authapiService/authentication-api-service.service';
import { ToastMessageService } from '../../services/toastMessageService/toast-message.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrl: './home-page.component.scss'
})
export class HomePageComponent implements OnInit, OnDestroy{
  private destroy$ = new Subject();
  item!: {name:string}[];
  constructor(private authservice:AuthenticationApiServiceService,private toastMsg: ToastMessageService, private routes:Router){}
  ngOnInit(): void {
    this.item=[
      {
        name:"Rockey"
      },
      {
        name:"Tommy"
      },
      {
        name:"Blackey"
      },
      {
        name:"Sweety"
      },
      {
        name:"Pug"
      }
    ]
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

  logout(){
    localStorage.clear();
    this.toastMsg.generateToast("success","Logout Successfully");
    this.routes.navigate(['login']);
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.complete();
  }
}
