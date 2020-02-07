import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-changepassword',
  templateUrl: './changepassword.component.html',
  styleUrls: ['./changepassword.component.css']
})
export class ChangepasswordComponent implements OnInit {

  email:any;
  constructor(private userService:UserService,private router:Router) { }

  changePass(myForm)
  {
    let data=myForm.form.value;
    this.email=sessionStorage['email']
    console.log(data);
    this.userService.change(data,this.email).subscribe((res)=>{
    this.router.navigate(['login']);
    },(error)=>{
      alert("Your password was not set")
    })
  }
  ngOnInit() {
  }

}
