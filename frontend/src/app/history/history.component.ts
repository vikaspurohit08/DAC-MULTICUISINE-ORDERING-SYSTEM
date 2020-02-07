import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-history',
  templateUrl: './history.component.html',
  styleUrls: ['./history.component.css']
})
export class HistoryComponent implements OnInit {

  constructor(private userService:UserService,private router:Router) { }

  userid:any;
  orderlist:any;
  cart:any;
  ngOnInit() {

    if(sessionStorage['email'])
    {
      console.log("order list loaded");
    this.userid=sessionStorage['userid'];
    this.userService.showHistory(this.userid).subscribe((res)=>{
      this.orderlist=res;
      
      console.log("data");
     
      sessionStorage['orders']=res;
      console.log(res['o_id']);
      sessionStorage.setItem("orderid",res['o_id']);
      
      console.log(this.orderlist);
  })
  }
  else
  {
    this.router.navigate(['login']);
  }

}
}
