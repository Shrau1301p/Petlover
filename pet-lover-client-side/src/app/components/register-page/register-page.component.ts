import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrl: './register-page.component.scss'
})
export class RegisterPageComponent {
  signForm = new FormGroup({
    email : new FormControl(''),
    password : new FormControl(''),
    confirm : new FormControl('')
  })
}
