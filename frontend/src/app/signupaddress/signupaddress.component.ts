import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signupaddress',
  templateUrl: './signupaddress.component.html',
  styleUrls: ['./signupaddress.component.css']
})
export class SignupaddressComponent implements OnInit {

  email:any;
  constructor(private userService:UserService,private router:Router) { }

  add(myForm)
  {
    let data = myForm.form.value;
    let email=sessionStorage['email'];
    console.log(email);
    let x= {
      "area":data.area,
      "street":data.street
    }
    console.log(data);
    this.userService.address(email,data).subscribe((res)=>{
      sessionStorage['email']=res['email'];
      this.router.navigate(['login']);
    },(error)=>{
      
    });
  }

  ngOnInit() {
  }

}
