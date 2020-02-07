package com.app.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.app.pojos.Locations;
import com.app.pojos.OrderStatus;
import com.app.pojos.Orders;
import com.app.pojos.UserRole;
import com.app.pojos.Users;

@Repository
@Transactional
public class UserDao implements IUserDao 
{
	@Autowired
	SessionFactory sf;
	
	//Users user;
	LocationDao ldao;
	
	IUserDao userdao;
	
	IOrderDao odao;
	
	@Override
	public Users registerUser(Users user) 
	{
		System.out.println(user);
		System.out.println("inside registerUser");
		sf.getCurrentSession().save(user);
		return user;
	}
	
	@Override
	public Users getUserById(Integer u_id)
	{
		System.out.println("inside getUserById");
		System.out.println(u_id);
		System.out.println();
		if(u_id!=null)
		{
			String jpql = "select u from Users u where user_id=:u_id";
			return sf.getCurrentSession().createQuery(jpql,Users.class).setParameter("u_id",u_id).getSingleResult();
		}
		return null;
		
	}
	
	

	@Override
	public Users login(Users user) 
	{
		System.out.println("pass bfr " + user.getEmail());
		System.out.println("pass bfr " + user.getPassword());
		String jpql="select u from Users u where u.email=:em and u.password=:pass";
		System.out.println("pass aft " + user.getPassword());
		return sf.getCurrentSession().createQuery(jpql,Users.class).setParameter("em",user.getEmail()).setParameter("pass", user.getPassword()).getSingleResult();
	}
	
	@Override
	public Users findByEmail(Users user) 
	{
		System.out.println(user);
		String jpql = "select u from Users u where u.email=:em";
		return sf.getCurrentSession().createQuery(jpql,Users.class).setParameter("em",user.getEmail()).getSingleResult();
	}
	
	@Override
	public Users findByEmail(String email) 
	{
		System.out.println("23453"+email);
		
		String jpql = "select u from Users u where u.email=:em";
		return sf.getCurrentSession().createQuery(jpql,Users.class).setParameter("em",email).getSingleResult();
	}
	
	@Override
	public int generateOtp() 
	{
		Random random = new Random();
		int num = random.nextInt(99999) + 99999;
		if (num < 100000 || num > 999999) 
		{
			num = random.nextInt(99999) + 99999;
			if (num < 100000 || num > 999999)
			{
				System.out.println("Unable to generate PIN at this time..");
			}
		}
		return num;
	}
	
	public void updateOtp(Users user)
	{
		sf.getCurrentSession().update(user);
	}
	
	public int resetpassword(String pass,String email)
	{
		//String email = user.getEmail();
		Users u = findByEmail(email);
		System.out.println(u);
		if(u!=null)
		{
			u.setPassword(pass);
			return 1;
		}
		else
			return 0;
		
	}

	@Override
	public List<Users> getCustomers(String role) 
	{
		System.out.println(role);
		String jpql="select u from Users u where u.role=:role";
		return sf.getCurrentSession().createQuery(jpql,Users.class).setParameter("role", UserRole.valueOf(role)).getResultList();
	}

	@Override
	public Integer addAddress(Locations loc,Users user) {
		System.out.println("inside add address"+ loc);
		//ArrayList<Locations> l= ldao.getLocation(loc);
		loc.removeLocation(user);
		loc.addLocation(user);
		
		sf.getCurrentSession().save(loc);
		sf.getCurrentSession().update(user);
		return 1;
	}

	@Override
	public List<Orders> getOrders() {
		String jpql="select o from Orders o";
		return sf.getCurrentSession().createQuery(jpql,Orders.class).getResultList();
	}

	@Override
	public List<Orders> getOrdersDtls(Users user) {
		String jpql="select o from Orders o where o.user=:user and o.o_status='PENDING'";
		
		return sf.getCurrentSession().createQuery(jpql,Orders.class).setParameter("user",user).getResultList();
	}
	
	@Override
	public List<Orders> getOrderHistory(Users user) {
		String jpql="select o from Orders o where o.user=:user ";
		return sf.getCurrentSession().createQuery(jpql,Orders.class).setParameter("user",user).getResultList();
	}

	@Override
	public Integer updateDelStatus(Orders order) {
		
		//String jpql = "update o from Orders o set o.o_status='DELIVERED' where o.user=:user";
		//Orders o = order.getO_id();
		order.setO_status(OrderStatus.DELIVERED);
		sf.getCurrentSession().update(order);
		return 1;
	}

	@Override
	public Orders getOrderById(int id) {
		String jpql="select o from Orders o where o_id=:id";
		return sf.getCurrentSession().createQuery(jpql,Orders.class).setParameter("id", id).getSingleResult();
	}

	@Override
	public Integer removeFoodItems(int fid, int oid) {
		return null;
	}

	@Override
	public Integer rmvOrder(int id) {
		Session hs = sf.getCurrentSession();
		//get Order details from id
		Orders o =hs.get(Orders.class, id);
		System.out.println(o);
		if(o!=null)
		{
			hs.delete(o);
			return 1;
		}
		else 
			return 0;
	}

	

	

	
}
