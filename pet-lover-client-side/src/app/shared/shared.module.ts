import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NavsidebarComponent } from './components/navsidebar/navsidebar.component';



@NgModule({
  declarations: [
    NavsidebarComponent
  ],
  imports: [
    CommonModule
  ],
  exports:[
    NavsidebarComponent
  ]
})
export class SharedModule { }
