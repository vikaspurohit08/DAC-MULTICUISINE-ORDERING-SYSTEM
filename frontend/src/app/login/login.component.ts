import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { EmtrService } from '../emtr.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private userService:UserService,private router:Router,private emService:EmtrService) { }

  login(loginForm)
  {
    
    let data = loginForm.form.value;
    //console.log(data);
    this.userService.login(data).subscribe((res)=>{
      sessionStorage['email']=res['email'];
      sessionStorage['user']=res;
      sessionStorage.setItem("userid",res['u_id']);
      //console.log(res)
      //alert(res['role']);
      this.emService.logInBtnSwitch(true);
      if(res['role']=='CUSTOMER')
      this.router.navigate(['restaurants']);
      else
      this.router.navigate(['admin']);
    },(error)=>{
      //alert(data.email);
      //alert(data.password);
      alert("Invalid Email or Password");
    })
  }
  signup()
  {
    this.router.navigate(['signup']);

  }

  cancel()
  {
    this.router.navigate(['']);
  }

  ngOnInit() {
    
      //alert("Logging You Out ...");
      //this.isLogin=false;
      delete sessionStorage['email'];
      delete sessionStorage['userid'];
      delete sessionStorage['orders'];
      delete sessionStorage['user'];
      //this.router.navigate(['login']);
    

  }

}
