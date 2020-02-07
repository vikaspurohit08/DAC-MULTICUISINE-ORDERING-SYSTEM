package com.app.controller;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.dao.*;
import com.app.pojos.*;

@RestController
@RequestMapping("/restaurant")
@CrossOrigin(origins = "*")
public class RestaurantController {

	@Autowired
	private IRestaurantDao rdao;
	@Autowired
	private ICityDao cdao;
	byte[] img;

	public RestaurantController() {
		System.out.println("Inside Parameterless Ctor of RestController");
	}

	@PostMapping("/add")
	public ResponseEntity<?> addNewRest(@RequestParam String rest_name,@RequestParam String street,
			@RequestParam String area,@RequestParam String city,@RequestParam String rest_contact, 
			@RequestParam String rest_email,@RequestParam String rest_type , @RequestParam(value = "rest_image", required = false) MultipartFile rest_image) {
			
			 System.out.println(rest_image.getOriginalFilename());
			 System.out.println(rest_contact);
			 System.out.println(area);
			 Restaurant restaurant = new Restaurant(rest_name, rest_contact, rest_email, RestStatus.AVAILABLE, 4.0f, RestType.valueOf(rest_type));
			 Locations location = new Locations(street, area);
			 List<City> cities= cdao.getAllCities();
			 City citytoloc = null;
			 for (City c : cities) {
				if(c.getCity().equals(city.toUpperCase()))
				{
					citytoloc = c;
				}
			}
			 citytoloc.addLocations(location);
			 
			 if (rest_image != null) {
			try {
				System.out.println(rest_image.getOriginalFilename());
				restaurant.setRest_image(rest_image.getBytes());
				System.out.println(restaurant);
				return new ResponseEntity<Integer>(rdao.addRestaurant(restaurant,location), CREATED);
			} catch (Exception e) {

				return new ResponseEntity<Restaurant>(new Restaurant(), INTERNAL_SERVER_ERROR);
			}
		}
		return null;
	}
	
	
	@GetMapping("/list")
	public ResponseEntity<?> getAllRestaurants()
	{
		System.out.println("Inside get all restaurants");
		List<Restaurant> restaurants = rdao.getRestaurants();
		if(restaurants.isEmpty())
			return new ResponseEntity<String>("No Restaurant", NO_CONTENT);
		return new ResponseEntity<List<Restaurant>>(restaurants , OK);
	}
	
	
	@GetMapping("/list/{rest_type}")
	public ResponseEntity<?> getRestaurantByType(@PathVariable String rest_type)
	{
		List<Restaurant> restaurants = rdao.getRestaurantsByType(rest_type.toUpperCase());
		System.out.println(restaurants);
		if(restaurants.isEmpty())
			return new ResponseEntity<String>("No Restaurant", NO_CONTENT);
		return new ResponseEntity<List<Restaurant>>(restaurants , OK);
	}
	
	
	//it has logic for both search by location and name
	@GetMapping("/list/search/{area}")
	public ResponseEntity<?> getRestaurantByLocation(@PathVariable String area)
	{
		List<Restaurant> restaurants = rdao.getRestaurantsByLocation(area);
		System.out.println(restaurants);
		if(restaurants.isEmpty())
			return new ResponseEntity<String>("No Restaurant", NO_CONTENT);
		return new ResponseEntity<List<Restaurant>>(restaurants , OK);
	}
	
	@GetMapping("/list/id/{rest_id}")
	public ResponseEntity<?> getRestaurantById(@PathVariable String rest_id)
	{
		System.out.println(rest_id);
		Restaurant restaurant = rdao.getRestaurantsById(Integer.parseInt(rest_id));
		System.out.println(restaurant);
		img = restaurant.getRest_image();
		if(restaurant == null)
			return new ResponseEntity<String>("No Restaurant", NO_CONTENT);
		return new ResponseEntity<Restaurant>(restaurant , OK);
	}
	
	
	@PostMapping("/update")
	public ResponseEntity<?> updateRestaurant(@RequestParam int rest_id ,@RequestParam String rest_name,@RequestParam String street,
			@RequestParam String area,@RequestParam String city,@RequestParam String rest_contact, 
			@RequestParam String rest_email, @RequestParam(value = "rest_image", required = false) MultipartFile rest_image )
	{
		System.out.println("new rest" + rest_name);
		System.out.println(city);
		System.out.println("new image");
		System.out.println(rest_image);
		Locations loc = new Locations(street, area);
		 List<City> cities= cdao.getAllCities();
		 City citytoloc = null;
		 for (City c : cities) {
			if(c.getCity().equals(city.toUpperCase()))
			{
				citytoloc = c;
			}
		}
		 citytoloc.addLocations(loc);
		 
		 
		 
		Restaurant restaurant = new Restaurant(rest_id,rest_name, rest_contact, rest_email, RestStatus.AVAILABLE, 4, RestType.FAMILYSTYLE);
		restaurant.setRest_image(img);
		return new ResponseEntity<Integer>(rdao.updateRestaurant(restaurant,loc), OK);
	}
	
	
	
}
