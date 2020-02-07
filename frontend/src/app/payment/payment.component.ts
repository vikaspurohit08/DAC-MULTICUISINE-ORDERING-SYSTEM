import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  constructor(private userServive:UserService,private router:Router) { }

  pay(paymentForm)
  {
    let data=paymentForm.form.value;
    console.log(data);
    if(data.pay== 1 || data.pay== 2 || data.pay== 3)
    {
      this.status();
      
      this.router.navigate(['restaurants'])
    }
  }

  status()
  {
    let data=sessionStorage['orderid'];
    this.userServive.statusCart(data).subscribe((res)=>{
      alert("Payment Successful");
      alert("Your Order Has Been Placed");
    },(error)=>{
      alert("Failed to place Order !!!");
    })
  }
  ngOnInit() {
    if(sessionStorage['email'])
    {

    }
    else{
      this.router.navigate(['login']);
    }
  }

}
