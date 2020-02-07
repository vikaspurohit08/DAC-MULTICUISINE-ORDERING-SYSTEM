import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  register(user)
  {
    return this.http.post("http://localhost:8082/multicuisine/user/signup",user);
  }

  login(user)
  {
    console.log(user);
    return this.http.post("http://localhost:8082/multicuisine/user/login",user);
  }

  forgot(user)
  {
    console.log(user);
    return this.http.post("http://localhost:8082/multicuisine/user/forgot",user);
  }

  otp(user,email)
  {
    console.log(user);
    return this.http.post("http://localhost:8082/multicuisine/user/otp?email="+email,user);
  }

  address(email,x)
  {
    return this.http.put("http://localhost:8082/multicuisine/user/address?email="+email,x);
  }

  change(data,email)
  {
    return this.http.post("http://localhost:8082/multicuisine/user/change?email="+email,data);
  }
  changeaddress(x,email)
  {
    return this.http.put("http://localhost:8082/multicuisine/user/delivery?email="+email,x);
  }

  showCart(user)
  {
    return this.http.get("http://localhost:8082/multicuisine/user/cart?id="+user);
  }

  statusCart(data)
  {
    return this.http.get("http://localhost:8082/multicuisine/user/updateStatus?id="+data);
  }

  showHistory(user)
  {
    return this.http.get("http://localhost:8082/multicuisine/user/history?id="+user);
  }

  listAllCustomers()
  {
    return this.http.get("http://localhost:8082/multicuisine/user/customer/CUSTOMER");
  }

  blockUser(id)
  {
    return this.http.get("http://localhost:8082/multicuisine/user/block?id="+id);
  }

  removeOrder(id)
  {
    return this.http.get("http://localhost:8082/multicuisine/user/remove?id="+id);
  }

  viewOrders()
  {
    return this.http.get("http://localhost:8082/multicuisine/user/orders");
  }

}