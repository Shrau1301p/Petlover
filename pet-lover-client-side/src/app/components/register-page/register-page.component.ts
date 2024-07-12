import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { AuthenticationApiServiceService } from '../../services/authapiService/authentication-api-service.service';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrl: './register-page.component.scss'
})
export class RegisterPageComponent implements OnInit{
  signForm!: FormGroup;

  constructor(private authservice:AuthenticationApiServiceService){
  }

  ngOnInit(): void {
    this.signForm = new FormGroup({
      email : new FormControl(''),
      password : new FormControl(''),
      confirm : new FormControl('')
    })  
  }

  async getData(){
    console.log(this.signForm.value);  
    const data={
      email: this.signForm.value.email,
      password : this.signForm.value.password
    }
    this.authservice.register(data).subscribe({
      next: (respo)=> {
        console.log("inside subscribe method",respo);
      },
      error: (error)=>{
        console.log("Something went wrong",error);
      }
    });
  }
  
}
