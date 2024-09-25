import { Component, inject, model, OnDestroy, OnInit, signal } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
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
  // profileForm!: FormGroup;
  item!: { name: string }[];
  userLoginFor: string | null = localStorage.getItem("isFirstTimeLogin");
  profile! :any;
  constructor(public dialog: MatDialog,private authservice:AuthenticationApiServiceService,private toastMsg: ToastMessageService) {}

  ngOnInit(): void {
      // this.profileForm = new FormGroup({
      //   dob : new FormControl(''),
      //   describtion : new FormControl('')
      // })    
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
    ]
  }
  addProfile(){

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
  
  async auth(){
    
    this.authservice.auth().pipe(takeUntil(this.destroy$)).subscribe({
      next: (respo:any)=> {
        console.log(respo);
        
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
