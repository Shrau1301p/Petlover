import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.scss'
})
export class LoginPageComponent {
  
  loginForm! : FormGroup;

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      email : new FormControl(''),
      password : new FormControl(''),
      confirm : new FormControl('')
    })  
  }

  login(): void{
    
  }
}
