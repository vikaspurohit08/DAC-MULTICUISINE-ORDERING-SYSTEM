package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "locations")
public class Locations {
	private Integer l_id;
	private String street;
	private String area;
	
	private City city;
	@JsonIgnore
	private List<Users> users = new ArrayList<>();
	@JsonIgnore
	private List<Restaurant> restaurants = new ArrayList<>();
	
	public Locations() {
		System.out.println("inside locations ctor");
	}

	public Locations(String street, String area) {
		this.street = street;
		this.area = area;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "location_id")
	public Integer getL_id() {
		return l_id;
	}

	public void setL_id(Integer l_id) {
		this.l_id = l_id;
	}
	@Column(name = "street",length = 50,nullable = false)
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	@Column(name = "area",length = 50,nullable = false)
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	@ManyToOne
	@JoinColumn(name = "city_id")
	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Locations [l_id=" + l_id + ", Street=" + street + ", Area=" + area + "]";
	}

	@OneToMany(mappedBy = "location",cascade=CascadeType.PERSIST,orphanRemoval=true)
	public List<Users> getUsers() {
		return users;
	}

	public void setUsers(List<Users> users) {
		this.users = users;
	}

	@OneToMany(mappedBy="location",cascade=CascadeType.PERSIST,orphanRemoval=true)
	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	public void addRestaurants(Restaurant restaurant)
	{
		restaurants.add(restaurant);
		restaurant.setLocation(this);
	}
	public void deleteRestaurant(Restaurant restaurant)
	{
		restaurants.remove(restaurant);
		restaurant.setLocation(null);
	}
	
	public void addLocation(Users user)
	{
		users.add(user);
		System.out.println(user);
		user.setLocation(this);
	}
	
	public void removeLocation(Users user)
	{
		users.remove(user);
		user.setLocation(null);
	}
	
}
