import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-delivery',
  templateUrl: './delivery.component.html',
  styleUrls: ['./delivery.component.css']
})
export class DeliveryComponent implements OnInit {

  email:any;
  constructor(private userService:UserService,private router:Router) { }

  details(deliveryForm)
  {
    let data=deliveryForm.form.value;
    
    console.log(data);
    this.email=sessionStorage['email'];
    this.userService.changeaddress(data,this.email).subscribe((res)=>{
      this.router.navigate(['payment']);
    },(error)=>{
      alert('Address was not entered');
      this.router.navigate(['payment']);
    })
  }

  ngOnInit() {
    if(sessionStorage['email'])
    {
      
    }
    else
    {
      this.router.navigate(['login']);
    }
  }

}
