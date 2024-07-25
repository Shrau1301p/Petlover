import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class ToastMessageService {

  constructor(private toastr: ToastrService) { }

  handleHttpMessageToast(status:number, message:string){
    if(status == 200)
      this.toastr.success(message);
    else if(status == 401)
      this.toastr.warning(message);
    else
      this.toastr.error(message);
  }

  generateToast(type:string, message:string){
    if(type == 'success')
      this.toastr.success(message)
    else if(type == 'warning')
      this.toastr.warning(message)
    else
      this.toastr.error(message);
  }
}
