import { Component, OnInit } from '@angular/core';
import { RestaurantlistService } from '../restaurantlist.service'

@Component({
  selector: 'app-managerestaurant',
  templateUrl: './managerestaurant.component.html',
  styleUrls: ['./managerestaurant.component.css']
})
export class ManagerestaurantComponent implements OnInit {

  restaurants:any;
  searchdata:any;
  constructor(private restaurantlistservice:RestaurantlistService) { }

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
