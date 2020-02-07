import { Component } from '@angular/core';
import { EmtrService } from './emtr.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'multicuisine';

  isLogin:false;

  constructor(private emService:EmtrService,private router:Router)
  {  }
  
  ngOnInit() {
 

    this.emService.getEmittedValueForLogbtnSwitch().subscribe(item => this.isLogin=item);
  }

  logout()
  {
    alert("Logging You Out ...");
    this.isLogin=false;
    delete sessionStorage['email'];
    delete sessionStorage['userid'];
    delete sessionStorage['orders'];
    delete sessionStorage['user'];
    delete sessionStorage['orderid'];
    this.router.navigate(['login']);
  }

home()
{

  if(sessionStorage['user.role']=='CUSTOMER')
  this.router.navigate(['restaurants']);
  else
  this.router.navigate(['admin']);
}


}