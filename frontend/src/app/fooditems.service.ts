import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class FooditemsService {

  constructor(private http:HttpClient) { }

  getFoodItemList(rest_id)
  {
    return this.http.get("http://localhost:8082/multicuisine/fooditems/list/" + rest_id);
  }

  getRestaurant(rest_id)
  {
    return this.http.get("http://localhost:8082/multicuisine/restaurant/list/id/" + rest_id);
  }

  addToCart(item,rest_id,userid)
  {
    console.log("item = " + item);
    console.log("rest number =" + rest_id);
    console.log(userid);
    return this.http.put("http://localhost:8082/multicuisine/orders/add/" + rest_id + "/" + userid , item);
  }

  update(fooditem)
  {
    console.log(fooditem);
    return this.http.post("http://localhost:8082/multicuisine/fooditems/update",fooditem);
  }

  removeItem(fi_id)
  {
    console.log(fi_id);
    return this.http.delete("http://localhost:8082/multicuisine/fooditems/delete/" + fi_id);
  }


}
