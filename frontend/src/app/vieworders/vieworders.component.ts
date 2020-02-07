import { Component, OnInit } from '@angular/core';
import { UserService } from './../user.service'

@Component({
  selector: 'app-vieworders',
  templateUrl: './vieworders.component.html',
  styleUrls: ['./vieworders.component.css']
})
export class ViewordersComponent implements OnInit {

  orders:any;
  constructor( private userservice:UserService ) { }

  ngOnInit() {
    this.userservice.viewOrders().subscribe((data)=>{
      console.log(data);
      this.orders = data;
    });
  }

}
