import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FeedListComponent } from './components/feed-list/feed-list.component';
import { ProfilePageComponent } from './components/profile-page/profile-page.component';
import { AddFeedPageComponent } from './components/add-feed-page/add-feed-page.component';

const routes: Routes = [
  {
    path:'feeds', 
    component:FeedListComponent
  },
  {
    path:'profile', 
    component: ProfilePageComponent
  },
  {
    path:'add-feed',
    component: AddFeedPageComponent
  },
  { 
    path: '', 
    redirectTo: 'feeds', 
    pathMatch: 'full' 
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomePageRoutingModule { }
