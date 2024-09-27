import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { FileUploader } from 'ng2-file-upload';
import { Subject } from 'rxjs';

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
  authTokenBearer: string | undefined = `Bearer ${localStorage.getItem('token') || undefined}`;

  public uploader: FileUploader = new FileUploader({
    url: 'http://localhost:8080/home/uploadImage', // Backend URL to upload the file
    itemAlias: 'feedImage',
    authToken: this.authTokenBearer,
    allowedFileType: ['image'],
    disableMultipart: true
  });

  constructor() { }

  ngOnInit(): void {
    this.addFeedForm = new FormGroup({
      caption : new FormControl('')
    })   
  }

  fileChangeEvent(event: any): void {
    this.file = event.target.files.item(0);
  }

  onSubmit():void{
    this.uploader.uploadAll();
  }

  ngOnDestroy(): void {
    this.destroy$.next(true);
    this.destroy$.complete();
  }
}
