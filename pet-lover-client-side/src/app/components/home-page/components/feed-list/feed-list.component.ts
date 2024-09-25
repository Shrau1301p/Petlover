import { Component, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Subject, takeUntil } from 'rxjs';
import { CreateProfileModalComponent } from '../../../../modal/create-profile-modal/create-profile-modal.component';
import { AuthenticationApiServiceService } from '../../../../services/authapiService/authentication-api-service.service';
import { ToastMessageService } from '../../../../services/toastMessageService/toast-message.service';

@Component({
  selector: 'app-feed-list',
  templateUrl: './feed-list.component.html',
  styleUrl: './feed-list.component.scss'
})

export class FeedListComponent implements OnInit, OnDestroy {
  private destroy$ = new Subject();
  
  item!: { name: string }[];
  userLoginFor: string | null = localStorage.getItem("isFirstTimeLogin");
  
  constructor(public dialog: MatDialog,private authservice:AuthenticationApiServiceService,private toastMsg: ToastMessageService) {}

  ngOnInit(): void {   
    this.item = [
      {
        name: "Rockey"
      },
      {
        name: "Tommy"
      },
      {
        name: "Blackey"
      },
      {
        name: "Sweety"
      },
      {
        name: "Pug"
      }
    ];

    
  }
 
  openDialog(): void {
    const dialogRef = this.dialog.open(CreateProfileModalComponent, {
      width: '300px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.authservice.addProfile(result).pipe(takeUntil(this.destroy$)).subscribe({
          next: (respo:any)=> {
            if(respo.statusCode == 200){
              console.log('Dialog result:', result);
            } 
          },
          error: ()=>{
            this.toastMsg.generateToast('error','something went wrong');
          }
        });
      }
    });
  }
  
  async auth() : Promise<void>{
    this.authservice.auth().pipe(takeUntil(this.destroy$)).subscribe({
      next: (respo:any)=> {
        console.log(respo);
      },
      error: ()=>{
        this.toastMsg.generateToast('error','something went wrong');
      }
    });
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.complete();
  }
}
