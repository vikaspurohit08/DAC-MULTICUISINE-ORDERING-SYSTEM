import { Component, OnInit } from '@angular/core';
import { RestaurantlistService } from '../restaurantlist.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FooditemsService } from '../fooditems.service';
import { DataserviceService } from '../dataservice.service';


declare var $: any;

@Component({
  selector: 'app-modifyrestaurant',
  templateUrl: './modifyrestaurant.component.html',
  styleUrls: ['./modifyrestaurant.component.css']
})
export class ModifyrestaurantComponent implements OnInit {

  restaurant:any;
  rest_id:any;
  rest_image:any;
  fooditems:any;
  fooditem:any;
  updateitem:any;
  updatelist:any;
  

  constructor( private restservice:RestaurantlistService,public foodservice:FooditemsService, private route:ActivatedRoute,private dataService:DataserviceService,private router:Router ) { }
  ngOnInit() {
    $(document).ready(function() {
      $('#show').click(function(){
        $('#collapse').show();
      });
      $('#hide').click(function(){
        $('#collapse').hide();
      })
    });
    this.rest_id = this.route.snapshot.paramMap.get("rest_id");
    
    this.restservice.getRestaurantById(this.rest_id).subscribe((data)=>{
      console.log(data);
      this.restaurant = data;
      this.rest_image = this.restaurant.rest_image;
    }) 

    this.foodservice.getFoodItemList(this.rest_id).subscribe((data)=>{
      console.log(data);
      this.fooditems = data;
      this.updatelist= data;
    })    

  }
  
  
  // onSelect(event){
  //   this.rest_image = event.target.files[0];
  //   var reader = new FileReader();
  //   reader.readAsDataURL(this.rest_image);

  //   reader.onload = (_event) => { 
  //     this.imgUrl = reader.result; 
  //   }
  // }
  
  onSelect(event){
    this.rest_image = event.target.files[0];
     var reader = new FileReader();
     reader.readAsDataURL(this.rest_image);
    // reader.onload = ((event) = {
    //   this.imgUrl = reader.result;  
    // }
      console.log(reader.result);
     reader.onload = (_event) => { 
        this.rest_image = reader.result; 
     }
  }



  insert(foodform)
{
  console.log(foodform.form.value);
  this.fooditem = foodform.form.value;

  console.log(this.fooditem);
  this.fooditems.push(this.fooditem);
  for(var i=0;i<this.fooditems.length;i++)
  {
    console.log("abc");
    console.log(this.fooditems[i]);
  }
  this.dataService.AddFood(this.fooditems,this.rest_id).subscribe((res2)=>{
    console.log(res2);
  })

}



update(fi_id)
{
  console.log(fi_id);
  for(var i=0;i<this.updatelist.length;++i)
  {
    if(this.updatelist[i].fi_id === fi_id)
    {
      this.updateitem = this.updatelist[i];
    }
  }
}


updatefood()
{
  let ress = this.foodservice.update(this.updateitem);
  console.log(ress);
  ress.subscribe((res)=>{
    console.log(res);
  });
}


updaterest()
{
  console.log("new image");
  console.log(this.rest_image);
  this.restaurant.rest_image = this.rest_image;

  let result = this.restservice.updaterest(this.restaurant,this.rest_image);
  result.subscribe((data)=>{
    console.log(data);
    this.router.navigate(["managerestaurants"])
  })

}

remove(fi_id)
{
  console.log(fi_id);
  this.foodservice.removeItem(fi_id).subscribe((data)=>{
    console.log(data);
  });
}





}
