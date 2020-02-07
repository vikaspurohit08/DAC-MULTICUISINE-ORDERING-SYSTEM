import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-forgot',
  templateUrl: './forgot.component.html',
  styleUrls: ['./forgot.component.css']
})
export class ForgotComponent implements OnInit {

  constructor(private userService:UserService,private router:Router) { }

  forgot(forgotForm)
  {
    let data=forgotForm.form.value;
    sessionStorage['email']=data.email;
    console.log(data);
    this.userService.forgot(data).subscribe((res)=>{
      this.router.navigate(['otp']);
    },(error)=>{
        alert("Invaid Email Address");
    })
  }

  ngOnInit() {
  }

}
