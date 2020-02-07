package com.app.pojos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="orders")
public class Orders {
	
	private Integer o_id;
	private int quantity;
	@JsonFormat(pattern = "dd-MM-yyyy")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date o_date;
	private String o_time;
	private String d_time;
	private OrderStatus o_status;
	private Users user;
	
	private List<FoodItems> fooditems = new ArrayList<FoodItems>();
	private Restaurant restaurant;
	
	public Orders() {
	System.out.println("Inside Orders Parameterless Ctor");
	}
	
	

	public Orders(int quantity, Date o_date, String o_time, String d_time, OrderStatus o_status) {
		
		this.quantity = quantity;
		this.o_date = o_date;
		this.o_time = o_time;
		this.d_time = d_time;
		this.o_status = o_status;
	}



	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "order_id")
	public Integer getO_id() {
		return o_id;
	}

	public void setO_id(Integer o_id) {
		this.o_id = o_id;
	}

	@Column(name="quantity",nullable = false)
	public int getQuantity() {
		return quantity;
	}

	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Column(name="order_date",nullable = false)
	@Temporal(TemporalType.DATE)
	public Date getO_date() {
		return o_date;
	}

	public void setO_date(Date o_date) {
		this.o_date = o_date;
	}

	@Column(name="order_time",length=10,nullable = false)
	public String getO_time() {
		return o_time;
	}

	public void setO_time(String o_time) {
		this.o_time = o_time;
	}

	@Column(name="delivery_time",length=10,nullable = false)
	public String getD_time() {
		return d_time;
	}

	public void setD_time(String d_time) {
		this.d_time = d_time;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="delivery_status",length=20,nullable = false)
	public OrderStatus getO_status() {
		return o_status;
	}

	
	public void setO_status(OrderStatus o_status) {
		this.o_status = o_status;
	}

	@ManyToOne
	@JoinColumn(name="user_id")
	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	@Fetch(value=FetchMode.SUBSELECT)
	@ManyToMany(mappedBy = "orders",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	public List<FoodItems> getFooditems() {
		return fooditems;
	}



	public void setFooditems(List<FoodItems> fooditems) {
		this.fooditems = fooditems;
	}


	@ManyToOne
	@JoinColumn(name="rest_id")
	public Restaurant getRestaurant() {
		return restaurant;
	}



	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	public void addFoodItem(FoodItems item)
	{
		this.fooditems.add(item);
	}
	public void removeFoodItem(FoodItems item)
	{
		this.fooditems.remove(item);
	}



	@Override
	public String toString() {
		return "Orders [o_id=" + o_id + ", quantity=" + quantity + ", o_date=" + o_date + ", o_time=" + o_time
				+ ", d_time=" + d_time + ", o_status=" + o_status + ", user=" + user + "]";
	}
	
	
	
}
