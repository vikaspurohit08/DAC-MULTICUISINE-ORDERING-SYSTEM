package com.app.dao;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.FoodItems;
import com.app.pojos.OrderStatus;
import com.app.pojos.Orders;
import com.app.pojos.Restaurant;
import com.app.pojos.Users;



@Repository
@Transactional
public class OrderDao implements IOrderDao {

	@Autowired
	SessionFactory sf;
	@Autowired
	IUserDao udao;
	@Autowired
	IRestaurantDao rdao;
	
	public Integer addToCart(FoodItems item, int rest_id,int user_id)
	{
		System.out.println(user_id);
		Date date = new Date();
		System.out.println("Date " + date.toString());
		int thrs = date.getHours();
		int tmin = date.getMinutes();
		int tsec = date.getSeconds();
		String timeoforder = thrs + ":" + tmin + ":" + tsec;
		
		System.out.println(thrs + ":" + tmin + ":" + tsec);
		Session session = sf.getCurrentSession();
		Orders order = new Orders(1, date, timeoforder, "00:00", OrderStatus.PENDING);
		FoodItems ft = session.get(FoodItems.class, item.getFi_id());
		System.out.println(order);
		Integer result = (Integer) session.save(order);
		System.out.println("result " + result);
		order = session.get(Orders.class, result);
		System.out.println("id = " + order.getO_id());
		order.addFoodItem(ft);
		ft.addOrder(order);
		Users u = udao.getUserById(user_id);
		System.out.println("pwd = " + u.getPassword());
		u.addOrder(order);
		Restaurant rest = rdao.getRestaurantsById(rest_id);
		rest.addOrder(order);
		System.out.println("after add order");
		session.update(order);
		System.out.println("after update order");
		session.update(ft);
		System.out.println("after update item");
		System.out.println("user = " + u);
		System.out.println("item = " + ft);
		return new Integer(1);
	}
}
