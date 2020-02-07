package com.app.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.app.pojos.*;
import com.app.dao.*;

@RestController
@RequestMapping("/fooditems")
@CrossOrigin(origins = "http://localhost:4200")
public class FoodItemsController {

	@Autowired
	IFoodDao fdao;
	@Autowired
	IRestaurantDao rdao;
	
	@GetMapping("/list/{rest_id}")
	public List<FoodItems> getAllItems(@PathVariable String rest_id)
	{
		System.out.println("rest id = " + rest_id);
		Restaurant restaurant = rdao.getRestaurantsById(Integer.parseInt(rest_id));
		System.out.println("restaurant = " + restaurant);
		List<FoodItems> fd = restaurant.getFooditems();
		System.out.println("fd = " + fd);
		for (FoodItems foodItems : fd) {
			System.out.println("fdop " + foodItems);
		}
		return fd;
	}
	
	
	@PutMapping("/add/{rest_id}")
	public ResponseEntity<?> addFoodItems(@PathVariable String rest_id, @RequestBody List<FoodItems> fooditems)
	{
		
		System.out.println("inside addfooditems " + rest_id);
		for (FoodItems foodItems2 : fooditems) {
			System.out.println(foodItems2);
		}
		return new ResponseEntity<List<Integer>>(fdao.addFoodItems(fooditems,Integer.parseInt(rest_id)), CREATED);
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> updateFoodItems(@RequestBody FoodItems fooditem)
	{
		System.out.println("inside update food items controller");
		System.out.println(fooditem);
		return new ResponseEntity<FoodItems>(fdao.updateItem(fooditem), HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{fi_id}")
	public ResponseEntity<?> removeFoodItem(@PathVariable String fi_id)
	{
		System.out.println(fi_id);
		FoodItems fooditem = fdao.getFoodItemById(Integer.parseInt(fi_id));
		return new ResponseEntity<Integer>(fdao.removeItem(fooditem), HttpStatus.OK);
	}
	
	
}
