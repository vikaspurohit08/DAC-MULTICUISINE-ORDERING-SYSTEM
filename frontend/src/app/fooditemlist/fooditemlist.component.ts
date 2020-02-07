import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FooditemsService } from '../fooditems.service';

@Component({
  selector: 'app-fooditemlist',
  templateUrl: './fooditemlist.component.html',
  styleUrls: ['./fooditemlist.component.css']
})
export class FooditemlistComponent implements OnInit {
  rest_id:any;
  itemslists:any;
  items:any = [];
  itemsincart:any = [];
  restaurant:any;
  message:any;
  userid:any;
  constructor( private route:ActivatedRoute, private fooditemservice: FooditemsService ) { }

  ngOnInit() {
    this.message = "";
    this.rest_id = this.route.snapshot.paramMap.get("rest_id");
    console.log(this.rest_id);
    this.userid = sessionStorage['userid'];
    console.log("userid" + this.userid);
    this.fooditemservice.getRestaurant(this.rest_id).subscribe((res)=>{
      console.log(res);
      this.restaurant = res;
      
    })

    this.fooditemservice.getFoodItemList(this.rest_id).subscribe((data)=>{
      console.log(data);
      this.itemslists = data;
      this.itemslists.forEach(element => {
        console.log(element);
        this.items.push(element);
      });
    })
  }

addToCart(item)
{
  this.message = item.item_name + " ADDED TO CART";
  console.log("items = " + item);
  this.fooditemservice.addToCart(item,this.rest_id,this.userid).subscribe((data)=>{
    console.log(data);
  })

}


}
