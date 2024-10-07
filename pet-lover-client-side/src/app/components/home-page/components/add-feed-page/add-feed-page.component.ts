import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { FileUploader } from 'ng2-file-upload';
import { Subject, takeUntil } from 'rxjs';
import { AuthenticationApiServiceService } from '../../../../services/authapiService/authentication-api-service.service';
import { ToastMessageService } from '../../../../services/toastMessageService/toast-message.service';
import { Router } from '@angular/router';
import { feed } from '../../../../constants/feed';
import { DomSanitizer,SafeUrl } from '@angular/platform-browser';

@Component({
  selector: 'app-add-feed-page',
  templateUrl: './add-feed-page.component.html',
  styleUrl: './add-feed-page.component.scss'
})
export class AddFeedPageComponent implements OnInit, OnDestroy {
  private destroy$ = new Subject();
  addFeedForm!: FormGroup;
  file!: File;
  id!: string;
  url!: SafeUrl;
  authTokenBearer: string | undefined = `Bearer ${localStorage.getItem('token') || undefined}`;

  public uploader: FileUploader = new FileUploader({
    url: 'http://localhost:8080/home/uploadImage', // Backend URL to upload the file
    itemAlias: 'feedImage',
    authToken: this.authTokenBearer,
    allowedFileType: ['image'],
    
  });

  constructor(private authservice:AuthenticationApiServiceService,private toastMsg: ToastMessageService, private routes:Router,private sanitizer: DomSanitizer) { }

  ngOnInit(): void {
    this.addFeedForm = new FormGroup({
      caption : new FormControl('')
    })   
  }

  fileChangeEvent(event: any): void {
    this.file = event.target.files.item(0);
    this.url = this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(this.file));
  }

  onSubmit():void{
    this.uploader.uploadAll();
    var data :feed ={
      caption : this.addFeedForm.value.caption,
    };
    this.authservice.addFeed(data).pipe(takeUntil(this.destroy$)).subscribe({
      next: (respo:any)=> {
        if(respo.statusCode == 200){
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
