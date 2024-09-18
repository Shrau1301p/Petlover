import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Subject } from 'rxjs';

@Component({
  selector: 'app-feed-list',
  templateUrl: './feed-list.component.html',
  styleUrl: './feed-list.component.scss'
})
export class FeedListComponent {
  private destroy$ = new Subject();
  profileForm!: FormGroup;
  item!: { name: string }[];
  userLoginFor: string | null = localStorage.getItem("isFirstTimeLogin");
  ngOnInit(): void {
      this.profileForm = new FormGroup({
        dob : new FormControl(''),
        describtion : new FormControl('')
      })    
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
}
