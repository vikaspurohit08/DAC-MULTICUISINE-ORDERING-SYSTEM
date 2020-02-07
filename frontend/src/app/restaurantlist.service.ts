import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';


@Injectable({
  providedIn: 'root'
})
export class RestaurantlistService {

  constructor( private http:HttpClient ) { }

  showRestaurants()
  {
    return this.http.get("http://localhost:8082/multicuisine/restaurant/list");
  }

  searchRestaurants(searchdata)
  {
    console.log(searchdata);
    return this.http.get("http://localhost:8082/multicuisine/restaurant/list/search/" + searchdata);
  }
  
  getRestaurantById(rest_id)
  {
    console.log(rest_id);
    return this.http.get("http://localhost:8082/multicuisine/restaurant/list/id/" + rest_id);
  }



  // const formdata = new FormData();
  // formdata.append("rest_name",restaurant.rest_name);
  // console.log(restaurant.rest_name);
  // formdata.append("street",restaurant.street);
  // formdata.append("area",restaurant.area);
  // formdata.append("city",restaurant.city);
  // formdata.append("rest_contact",restaurant.rest_contact);
  // formdata.append("rest_email",restaurant.rest_email);
  // formdata.append("rest_image",rest_image);
  // console.log(rest_image);
  // console.log(formdata);
  updaterest(restaurant,rest_image)
  {
    console.log("Inside rest service");
    console.log(restaurant);
    console.log(rest_image);
    console.log("aft img");
    var formdata = new FormData();
    formdata.append("rest_id",restaurant.rest_id);
    formdata.append("rest_name",restaurant.rest_name);
    formdata.append("street",restaurant.location.street);
    formdata.append("area",restaurant.location.area);
    formdata.append("city",restaurant.location.city.city);
    formdata.append("rest_contact",restaurant.rest_contact);
    formdata.append("rest_email",restaurant.rest_email);
    formdata.append("rest_image",rest_image);

    return this.http.post("http://localhost:8082/multicuisine/restaurant/update",formdata);
  }


}
