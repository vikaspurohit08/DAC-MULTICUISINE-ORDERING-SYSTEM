import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { error } from 'util';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private userService:UserService,private router:Router) { 

  }

  register(myForm)
  {
    let data=myForm.form.value;
    console.log(data);
    this.userService.register(data).subscribe((res)=>{
      sessionStorage['email']=res['email'];
      this.router.navigate(['address']);
    },(error)=>{
        console.log(error);
    }
    )
  }

  ngOnInit() {
  }

}
