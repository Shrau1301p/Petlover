import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatDividerModule } from '@angular/material/divider';
import { MatListModule } from '@angular/material/list';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { FileUploadModule } from 'ng2-file-upload';
import { MatIcon } from '@angular/material/icon';
import { ReactiveFormsModule } from '@angular/forms';

import { HomePageRoutingModule } from './home-page-routing.module';
import { FeedListComponent } from './components/feed-list/feed-list.component';
import { ProfilePageComponent } from './components/profile-page/profile-page.component';
import { SharedModule } from '../../shared/shared.module';
import { AddFeedPageComponent } from './components/add-feed-page/add-feed-page.component';
import { ChatPageComponent } from './components/chat-page/chat-page.component';
import { SettingPageComponent } from './components/setting-page/setting-page.component';

@NgModule({
  declarations: [ 
    FeedListComponent, ProfilePageComponent, AddFeedPageComponent, ChatPageComponent, SettingPageComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    HomePageRoutingModule,
    SharedModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatDividerModule,
    MatListModule,
    MatIcon,
    FileUploadModule,
  ]
})
export class HomePageModule { }
