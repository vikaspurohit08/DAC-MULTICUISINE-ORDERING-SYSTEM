import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-otp',
  templateUrl: './otp.component.html',
  styleUrls: ['./otp.component.css']
})
export class OtpComponent implements OnInit {

  email:any;
  constructor(private userService:UserService,private router:Router) { }

  otp(otpForm)
  {
    let data=otpForm.form.value;
    console.log(data);
    this.email=sessionStorage['email'];
    this.userService.otp(data,this.email).subscribe((res)=>{
      
      this.router.navigate(['change']);
    },(error)=>{
      alert("Invalid Otp");
      
    })
  }

  ngOnInit() {
  }

}
