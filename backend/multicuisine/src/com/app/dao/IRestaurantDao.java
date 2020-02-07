package com.app.dao;

import java.util.List;

import com.app.pojos.*;

public interface IRestaurantDao {

	public Integer addRestaurant(Restaurant restaurant,Locations location);
	public List<Restaurant> getRestaurants();
	public List<Restaurant> getRestaurantsByType(String rest_type);
	public List<Restaurant> getRestaurantsByLocation(String area);
	/*public List<Restaurant> getRestaurantsByName(String rest_name);*/
	public Restaurant getRestaurantsById(int id);
	public int updateRestaurant(Restaurant restaurant,Locations loc);
}
