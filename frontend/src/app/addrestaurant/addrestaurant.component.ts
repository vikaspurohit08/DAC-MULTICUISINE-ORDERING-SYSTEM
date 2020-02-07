import { Component, OnInit } from '@angular/core';
import { DataserviceService } from '../dataservice.service';
import { Router } from '@angular/router'
import { $ } from 'protractor';
import { listLazyRoutes } from '@angular/compiler/src/aot/lazy_routes';
import { RestaurantlistComponent } from '../restaurantlist/restaurantlist.component';

@Component({
  selector: 'app-addrestaurant',
  templateUrl: './addrestaurant.component.html',
  styleUrls: ['./addrestaurant.component.css']
})
export class AddrestaurantComponent implements OnInit {
  rest_image:any;
  restaurant:any;
  imgUrl:any;
  fooditem:any;
  fooditems:any=[];
  resttype:any=[];
  constructor( public dataService:DataserviceService,public router:Router) { }

  ngOnInit() {

  let result = this.dataService.getRestType().subscribe((data)=>
  {
    console.log(data);
    for(var i=0;i<7;++i)
    {
      this.resttype.push(data[i]);
      console.log(data[i]);
    }
    console.log(this.resttype);
  })
    

  console.log("abc"+result);
  // result.subscribe((data)=>
  // {
  //   console.log(data);
  //   this.resttype=data;
  // });
  }


  onSelect(event){
    this.rest_image = event.target.files[0];
    var reader = new FileReader();
    reader.readAsDataURL(this.rest_image);
    // reader.onload = ((event) = {
    //   this.imgUrl = reader.result;  
    // }

    reader.onload = (_event) => { 
      this.imgUrl = reader.result; 
    }
  }

  add(myform){
    console.log(myform.form.value);
    this.restaurant = myform.form.value;

    console.log(this.restaurant.rest_type);
    this.dataService.AddRestaurant(this.restaurant,this.rest_image).subscribe((res)=>{
      console.log(res);
      
    this.dataService.AddFood(this.fooditems,res).subscribe((res2)=>{
      console.log(res2);
    })
      this.router.navigate(['addrestaurant']);
    });
  }

insert(foodform)
{
  console.log(foodform.form.value);
  this.fooditem = foodform.form.value;

  console.log(this.fooditem);
  this.fooditems.push(this.fooditem);
  for(var i=0;i<this.fooditems.length;i++)
  {
    console.log(this.fooditems[i]);
  }
}
 

}





