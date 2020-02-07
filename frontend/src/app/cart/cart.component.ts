import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  userid:any;
  orderlist:any;
  i:any;

  constructor(private userService:UserService,private router:Router) { }

  cart(cartForm)
  {
    //let data=cartForm.form.value;
    console.log(cartForm.form.value);
    //sessionStorage['cartitems']=data;
    this.router.navigate(['delivery']);
  }

  remove(oid)
  {
    this.userService.removeOrder(oid).subscribe((res)=>{
      console.log(res);
    })
  }

  ngOnInit() {
    if(sessionStorage['email'])
    {
      console.log("order list loaded");
      this.userid=sessionStorage['userid'];
      this.userService.showCart(this.userid).subscribe((res)=>{
      this.orderlist=res;
      
      // console.log(Object.values(res));
      // Object.values(res).forEach(element => {
      //   this.i = element;
      //   console.log(this.i);
      // });
      console.log("data");
      console.log(this.orderlist);
      sessionStorage['orders']=res;
      console.log(this.orderlist[0].o_id);
      
      
      sessionStorage.setItem("orderid",res[0].o_id);
      //sessionStorage.setItem("orderid",res.o_id); 

      //console.log(this.orderlist.fooditems);
      console.log(this.orderlist);
    })
    }
    else
    {
      this.router.navigate(['login']);
    }

  }

}
