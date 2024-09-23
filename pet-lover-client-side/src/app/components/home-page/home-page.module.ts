import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { HomePageRoutingModule } from './home-page-routing.module';
import { FeedListComponent } from './components/feed-list/feed-list.component';
import { ProfilePageComponent } from './components/profile-page/profile-page.component';
import { ReactiveFormsModule } from '@angular/forms';
import { SharedModule } from '../../shared/shared.module';
import { NavsidebarComponent } from '../../shared/components/navsidebar/navsidebar.component';
// import { HomePageComponent } from './home-page.component';


@NgModule({
  declarations: [ 
    FeedListComponent, ProfilePageComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    HomePageRoutingModule,
    SharedModule
  ]
})
export class HomePageModule { }
