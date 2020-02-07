package com.app.dao;

import com.app.pojos.*;

import java.util.List;

import javax.persistence.*;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public class RestaurantDao implements IRestaurantDao {

	@Autowired
	SessionFactory sf;
	
	public Integer addRestaurant(Restaurant restaurant,Locations location)
	{
		System.out.println(restaurant + "  \n  " + location);
		
		sf.getCurrentSession().save(location);
		location.addRestaurants(restaurant);
		System.out.println(restaurant + "  \n  " + location);
		return (Integer) sf.getCurrentSession().save(restaurant);
	}
	
	public List<Restaurant> getRestaurants()
	{
		String jpql = "select r from Restaurant r left outer join fetch r.location";
		List<Restaurant> restaurants = sf.getCurrentSession().createQuery(jpql, Restaurant.class).getResultList();
		return restaurants;
	}
	
	public List<Restaurant> getRestaurantsByType(String rest_type)
	{
		String jpql = "select r from Restaurant r left outer join fetch r.location where r.rest_type=:rest_type";
		return sf.getCurrentSession().createQuery(jpql, Restaurant.class).setParameter("rest_type", RestType.valueOf(rest_type)).getResultList();
	}
	
	public List<Restaurant> getRestaurantsByLocation(String area)
	{
		String jpql = "select r from Restaurant r left outer join fetch r.location where r.location.area=:area or r.rest_name=:area";
		return sf.getCurrentSession().createQuery(jpql, Restaurant.class).setParameter("area", area).getResultList();
	}

	@Override
	public Restaurant getRestaurantsById(int rest_id) {
		System.out.println("inside get rest by id");
		String jpql = "select r from Restaurant r left outer join fetch r.location where r.rest_id = :rest_id";
		return sf.getCurrentSession().createQuery(jpql, Restaurant.class).setParameter("rest_id", rest_id).getSingleResult();
	}
	
	public int updateRestaurant(Restaurant restaurant,Locations loc)
	{
		sf.getCurrentSession().save(loc);
		restaurant.setLocation(loc);
		 sf.getCurrentSession().update(restaurant);
		 return 1;
	}
	
	
}
