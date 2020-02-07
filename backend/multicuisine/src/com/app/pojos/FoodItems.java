package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "fooditems")
public class FoodItems {

	private Integer fi_id;
	private String item_name;
	private Double unit_price;
	private Category category;
	@JsonBackReference
	private List<Restaurant> restaurants = new ArrayList<Restaurant>();
	@JsonBackReference
	private List<Orders> orders = new ArrayList<>();
	
	
	public FoodItems() {
		System.out.println("inside fooditems ctor");
	}

	public FoodItems(String item_name, Double unit_price, Category category) {
		
		this.item_name = item_name;
		this.unit_price = unit_price;
		this.category = category;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fooditem_id")
	public Integer getFi_id() {
		return fi_id;
	}

	public void setFi_id(Integer fi_id) {
		this.fi_id = fi_id;
	}
	@Column(name = "itemname",length = 30,nullable = false)
	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name.toUpperCase();
	}
	@Column(name = "unit_price",nullable = false)
	public Double getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(Double unit_price) {
		this.unit_price = unit_price;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name = "category",nullable = false)
	public Category getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = Category.valueOf(category.toUpperCase());
	}
	
	
	public void setCategory(Category category)
	{
		this.category = category;
	}

	
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE},fetch=FetchType.EAGER)
	@Fetch(value=FetchMode.SUBSELECT)
	@JoinTable(name="rest_food",joinColumns= @JoinColumn(name="food_id"),inverseJoinColumns=@JoinColumn(name="rest_id"))
	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}

	//@Fetch(value=FetchMode.SUBSELECT)
	@ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
	@JoinTable(name="order_food",joinColumns= @JoinColumn(name="food_id"),inverseJoinColumns=@JoinColumn(name="order_id"))
	public List<Orders> getOrders() {
		return orders;
	}

	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "FoodItems [fi_id=" + fi_id + ", item_name=" + item_name + ", unit_price=" + unit_price + ", category="
				+ category + "]";
	}
	
	
	public void addRestaurant(Restaurant restaurant)
	{
		this.restaurants.add(restaurant);
	}
	public void removeRestaurant(Restaurant restaurant)
	{
		this.restaurants.remove(restaurant);
	}
	
	public void addOrder(Orders order)
	{
		this.orders.add(order);
	}
	public void removeOrder(Orders order)
	{
		this.orders.remove(order);
	}
	
}
