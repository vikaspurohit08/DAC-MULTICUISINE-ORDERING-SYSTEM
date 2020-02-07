package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.dao.IOrderDao;
import com.app.pojos.FoodItems;

@CrossOrigin(origins = "*")
@Controller
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	IOrderDao odao;
	/*http://localhost:8082/multicuisine/orders/add/19/1
*/	@PutMapping("/add/{rest_id}/{userid}")
	public ResponseEntity<Integer> addOrder(@RequestBody FoodItems item, @PathVariable String rest_id , @PathVariable String userid)
	{
		System.out.println("rest_id " + rest_id);
		System.out.println("item " + item);
		System.out.println("user " + userid);
		return new ResponseEntity<Integer>(odao.addToCart(item, Integer.parseInt(rest_id), Integer.parseInt(userid)),HttpStatus.OK );
	}
	
}
