import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DataserviceService {

  constructor( private http:HttpClient ) { }

  AddRestaurant(restaurant,rest_image){
    console.log(restaurant);
    const formdata = new FormData();
    formdata.append("rest_name",restaurant.rest_name);
    console.log(restaurant.rest_name);
    formdata.append("street",restaurant.street);
    formdata.append("area",restaurant.area);
    formdata.append("city",restaurant.city);
    formdata.append("rest_contact",restaurant.rest_contact);
    formdata.append("rest_email",restaurant.rest_email);
    formdata.append("rest_type",restaurant.rest_type);
    formdata.append("rest_image",rest_image);
    console.log(rest_image);
    console.log(formdata);
    return this.http.post("http://localhost:8082/multicuisine/restaurant/add",formdata);
}

AddFood(fooditems,res){
  
  console.log(fooditems);
  return this.http.put("http://localhost:8082/multicuisine/fooditems/add/" + res,fooditems);

}

getRestType()
{
  return this.http.get("http://localhost:8082/multicuisine/lists/resttypes");
}

}
