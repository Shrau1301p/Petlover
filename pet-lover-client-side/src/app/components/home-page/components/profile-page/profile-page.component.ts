import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject, takeUntil } from 'rxjs';
import { ToastMessageService } from '../../../../services/toastMessageService/toast-message.service';
import { AuthenticationApiServiceService } from '../../../../services/authapiService/authentication-api-service.service';

@Component({
  selector: 'app-profile-page',
  templateUrl: './profile-page.component.html',
  styleUrl: './profile-page.component.scss'
})
export class ProfilePageComponent implements OnInit, OnDestroy{
  private destroy$ = new Subject();
  
  totalPost!:number;
  feeds!:any[];
  constructor(private authservice:AuthenticationApiServiceService,private toastMsg: ToastMessageService){}
  
  ngOnInit(): void {
    this.authservice.getFeedsByUser().pipe(takeUntil(this.destroy$)).subscribe({
      next: (respo:any)=> {
        if(respo.statusCode == 200){
          this.feeds = respo.data;
          this.totalPost = this.feeds.length;
        } 
      },
      error: ()=>{
        this.toastMsg.generateToast('error','something went wrong');
      }
    });
  }

  editProfile(){
    console.log("edit");
  }
  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.complete();
  }
}
