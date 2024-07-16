import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { AuthenticationApiServiceService } from '../../services/authapiService/authentication-api-service.service';
import { responseMsg } from '../../constants/responseMsg';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-register-page',
  templateUrl: './register-page.component.html',
  styleUrl: './register-page.component.scss'
})
export class RegisterPageComponent implements OnInit{
  signForm!: FormGroup;

  constructor(private authservice:AuthenticationApiServiceService,private toastr: ToastrService){
  }

  ngOnInit(): void {
    this.signForm = new FormGroup({
      email : new FormControl(''),
      password : new FormControl(''),
      confirm : new FormControl('')
    })  
  }

  async getData(){
    const data={
      email: this.signForm.value.email,
      password : this.signForm.value.password
    }
    this.authservice.register(data).subscribe({
      next: (respo:any)=> {
        if(respo.statusCode == 200){
          this.toastr.success(respo.message);
        } else {
          this.toastr.warning(respo.message);
        }
      },
      error: (error)=>{
        this.toastr.warning("Something went wrong");
      }
    });
  }
  
}
