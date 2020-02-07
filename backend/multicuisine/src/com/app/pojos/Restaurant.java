package com.app.pojos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="restaurant")
public class Restaurant {

	private Integer rest_id;
	private String rest_name;
	private String rest_contact;
	private String rest_email;
	private RestStatus rest_status;
	private float rest_rating;
	
	private byte[] rest_image;
	private RestType rest_type;
	private Locations location;
	@JsonIgnore
	private List<FoodItems> fooditems = new ArrayList<FoodItems>();
	@JsonIgnore
	private List<Orders> orders = new ArrayList<>(); 
	
	
	public Restaurant() {
		System.out.println("Restaurant Parameterless Ctor");
	}
	
	public Restaurant(String rest_name, String rest_contact, String rest_email, RestStatus rest_status,
			float rest_rating, RestType rest_type) {
		
		this.rest_name = rest_name;
		this.rest_contact = rest_contact;
		this.rest_email = rest_email;
		this.rest_status = rest_status;
		this.rest_rating = rest_rating;
		this.rest_type = rest_type;
	}
	
	
	
	
	
	
	public Restaurant(Integer rest_id, String rest_name, String rest_contact, String rest_email, RestStatus rest_status,
			float rest_rating, RestType rest_type) {
		super();
		this.rest_id = rest_id;
		this.rest_name = rest_name;
		this.rest_contact = rest_contact;
		this.rest_email = rest_email;
		this.rest_status = rest_status;
		this.rest_rating = rest_rating;
		this.rest_image = rest_image;
		this.rest_type = rest_type;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getRest_id() {
		return rest_id;
	}
	public void setRest_id(Integer rest_id) {
		this.rest_id = rest_id;
	}
	
	@Column(length = 100,nullable = false)
	public String getRest_name() {
		return rest_name;
	}
	public void setRest_name(String rest_name) {
		this.rest_name = rest_name;
	}
	
	@Column(length = 15,nullable = false)
	public String getRest_contact() {
		return rest_contact;
	}
	public void setRest_contact(String rest_contact) {
		this.rest_contact = rest_contact;
	}
	
	@Column(length = 50,nullable = false,unique=true)
	public String getRest_email() {
		return rest_email;
	}
	public void setRest_email(String rest_email) {
		this.rest_email = rest_email;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(length = 50,nullable = false)
	public RestStatus getRest_status() {
		return rest_status;
	}
	public void setRest_status(RestStatus rest_status) {
		this.rest_status = rest_status;
	}
	
	@Column(nullable = false)
	public float getRest_rating() {
		return rest_rating;
	}
	public void setRest_rating(float rest_rating) {
		this.rest_rating = rest_rating;
	}
	
	@Lob
	@Column(length=16777215)
	public byte[] getRest_image() {
		return rest_image;
	}
	public void setRest_image(byte[] rest_image) {
		this.rest_image = rest_image;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(length = 50,nullable = false)
	public RestType getRest_type() {
		return rest_type;
	}

	public void setRest_type(RestType rest_type) {
		this.rest_type = rest_type;
	}

	@ManyToOne
	@JoinColumn(name="location_id")
	public Locations getLocation() {
		return location;
	}


	public void setLocation(Locations location) {
		this.location = location;
	}

	@ManyToMany(mappedBy = "restaurants",fetch=FetchType.EAGER)
	public List<FoodItems> getFooditems() {
		return fooditems;
	}


	public void setFooditems(List<FoodItems> fooditems) {
		this.fooditems = fooditems;
	}

	@OneToMany(mappedBy="restaurant",cascade = CascadeType.PERSIST,orphanRemoval=true)
	public List<Orders> getOrders() {
		return orders;
	}


	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}


	@Override
	public String toString() {
		return "Restaurant [rest_id=" + rest_id + ", rest_name=" + rest_name + ", rest_contact=" + rest_contact
				+ ", rest_email=" + rest_email + ", rest_status=" + rest_status + ", rest_rating=" + rest_rating
				+ ", rest_type=" + rest_type + ", location=" + location + "]";
	}
	
	
	public void addFoodItems(FoodItems fooditems)
	{
		this.fooditems.add(fooditems);
	}
	public void removeFoodItems(FoodItems fooditems)
	{
		this.fooditems.remove(fooditems);
	}
	
	public void addOrder(Orders order)
	{
		this.orders.add(order);
		order.setRestaurant(this);
	}
	public void removeOrder(Orders order)
	{
		this.orders.remove(order);
		order.setRestaurant(null);
	}
	
	
}
