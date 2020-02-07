import { Component, OnInit } from '@angular/core';
import { RestaurantlistService } from '../restaurantlist.service'

@Component({
  selector: 'app-restaurantlist',
  templateUrl: './restaurantlist.component.html',
  styleUrls: ['./restaurantlist.component.css']
})
export class RestaurantlistComponent implements OnInit {

  constructor(private restaurantlistservice:RestaurantlistService ) { }
  restaurants:any = [];
  temp:any=[];
  searchdata:any;
  noofcol = 0;

  ngOnInit() {
    console.log("restaurant list loaded");
    let restaurants = this.restaurantlistservice.showRestaurants();
    
    restaurants.subscribe((data)=>{
    this.restaurants = data;
    console.log(this.restaurants)
    })
  }

  search(searchform)
  {
    console.log("bfr");
    console.log(searchform.form.value.searchform);
    console.log("aft");
    this.searchdata = searchform.form.value.searchform;
    this.restaurantlistservice.searchRestaurants(this.searchdata).subscribe((res)=>{
      console.log(res);
      this.restaurants = res;
    });
  }

  
  
}
