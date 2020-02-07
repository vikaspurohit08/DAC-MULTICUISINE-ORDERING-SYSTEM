import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-customerlist',
  templateUrl: './customerlist.component.html',
  styleUrls: ['./customerlist.component.css']
})
export class CustomerlistComponent implements OnInit {

  constructor(private userService:UserService,private router:Router) { }

  customerlist:any;
  
  blockUser(id)
  {
    this.userService.blockUser(id).subscribe((res)=>
    {
      
    })
  }

  ngOnInit() {
    
    this.userService.listAllCustomers().subscribe((res)=>{
      console.log(res);
      this.customerlist=res;
    })
  }

}
