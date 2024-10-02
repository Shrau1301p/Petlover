import { ChangeDetectionStrategy, Component, inject, model, OnDestroy, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

import { Profile } from '../../constants/profile';
import { FormControl, FormGroup } from '@angular/forms';
import { FileUploader } from 'ng2-file-upload';
import { Subject, takeUntil } from 'rxjs';
import { AuthenticationApiServiceService } from '../../services/authapiService/authentication-api-service.service';
import { ToastMessageService } from '../../services/toastMessageService/toast-message.service';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-create-profile-modal',
  templateUrl: './create-profile-modal.component.html',
  styleUrl: './create-profile-modal.component.scss',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class CreateProfileModalComponent implements OnInit, OnDestroy {
  
  private destroy$ = new Subject();
  
  profileForm!:FormGroup;
  data!:Profile;
  file!: File;
  id!: string;
  url!: SafeUrl;
  authTokenBearer : string | undefined = `Bearer ${localStorage.getItem('token') || undefined}` 
  
  public uploader: FileUploader = new FileUploader({
    url: 'http://localhost:8080/home/uploadImage', // Backend URL to upload the file
    itemAlias: 'profileImage',
    authToken: this.authTokenBearer,
    allowedFileType: ['image'],
    disableMultipart :true,
  });
  
  constructor(private dialogRef: MatDialogRef<CreateProfileModalComponent>,private authservice:AuthenticationApiServiceService,private toastMsg: ToastMessageService,private sanitizer:DomSanitizer){}
  
  ngOnInit(): void {
    this.profileForm = new FormGroup({
      name : new FormControl(''),
      dob : new FormControl(''),
      describtion : new FormControl(''),
      category : new FormControl('')
    })   

    this.authservice.auth().pipe(takeUntil(this.destroy$)).subscribe({
      next: (respo:any)=> {
        this.id = respo.data.id;
      },
      error: ()=>{
        this.toastMsg.generateToast('error','something went wrong');
      }
    });
  }
  
  fileChangeEvent(event: any): void {
    this.file = event.target.files.item(0);
    this.url = this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(this.file));
    // console.log(this.url);
  }
 
  onSubmit(): void {
    this.uploader.uploadAll();
    this.data  = {
      id: this.id,
      pet_name : this.profileForm.value.name,
      dob_date: this.profileForm.value.dob,
      category : this.profileForm.value.category,
      describtion : this.profileForm.value.describtion, 
    }
    // this.authservice.uploadProfile(this.file).pipe(takeUntil(this.destroy$)).subscribe({
    //   next: (respo:any)=> {
        
    //   },
    //   error: ()=>{
    //     this.toastMsg.generateToast('error','something went wrong');
    //   }
    // });
    this.dialogRef.close(this.data);
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.complete();
  }
}
