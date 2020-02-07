package com.app.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatus.*;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.app.dao.ILocationDao;

import com.app.dao.IUserDao;

import com.app.pojos.FoodItems;
import com.app.pojos.Locations;
import com.app.pojos.Orders;
import com.app.pojos.UserRole;
import com.app.pojos.Users;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	IUserDao iuserdao;
		
	@Autowired
	ILocationDao ldao;
	
	@Autowired
	HttpSession hs;
	
	@Autowired
	JavaMailSender mailsender;
	
	@PostMapping("/signup")
	public Users register(@RequestBody Users user)
	{
		System.out.println(user);
			if(user !=null)
			{
				user.setRole(UserRole.CUSTOMER);
				user.setOtp(123456);
				//hs.setAttribute("OTP", otp);
				String msg="Dear"+user.getFirstname()+"You have successfully registered to our website, we recommend you to not share your password with anyone.";
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(user.getEmail());
				mailMessage.setSubject("Registration Successful");
				mailMessage.setText(msg);
				try
				{
					System.out.println("inside try block");
					mailsender.send(mailMessage);
				}
				catch (MailException e) 
				{
					System.out.println("inside mail exception");
					e.printStackTrace();
				}
			}
		return iuserdao.registerUser(user);
	}

	@PutMapping("/address")
	public ResponseEntity<?> addAddress(@RequestBody Locations loc,@RequestParam String email)
	{
		Users user = iuserdao.findByEmail(email);
		System.out.println(user);
		System.out.println("getUser "+loc);
		ArrayList<Locations> l= ldao.getLocation(loc);
		if(l==null | email!=null)
		{
			System.out.println("inside if loop");
			int k = iuserdao.addAddress(loc,user);
			return new ResponseEntity<Integer>(k,HttpStatus.OK);
			
		}
		else 
			
			return new ResponseEntity<Integer>(0,HttpStatus.OK);
		 
	}
	
	@PutMapping("/delivery")
	public ResponseEntity<?> addTempAddress(@RequestBody Locations loc,@RequestParam String email)
	{
		Users user = iuserdao.findByEmail(email);
		System.out.println(user);
		System.out.println("getUser "+loc);
		ArrayList<Locations> l= ldao.getLocation(loc);
		if(l==null | email!=null)
		{
			System.out.println("inside if loop");
			int k = iuserdao.addAddress(loc,user);
		
			return new ResponseEntity<Integer>(k,HttpStatus.OK);
		}
		else 
			
			return new ResponseEntity<Integer>(0,HttpStatus.OK);
		}
	
	@PostMapping("/login")
	public Users login(@RequestBody Users user)
	{
		System.out.println(user);
		return iuserdao.login(user);
	}

	@PostMapping("/forgot")
	public Integer processForgotPassword(@RequestBody Users user,HttpServletRequest request,Model map,HttpSession hs)
	{
		
		try
		{
			System.out.println("inside processForgotPassword "+user);
			user = iuserdao.findByEmail(user);
			System.out.println("findByEmail  "+user);
			//hs.setAttribute("user", user);
			if(user !=null)
			{
				int otp = iuserdao.generateOtp();
				user.setOtp(otp);
				iuserdao.updateOtp(user);
				
				System.out.println("storing in session "+otp);
				String msg="Your one time password for forgot password is = "+otp;
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(user.getEmail());
				mailMessage.setSubject("One Time Password");
				mailMessage.setText(msg);
				try
				{
					mailsender.send(mailMessage);
				}
				catch (MailException e) 
				{
					System.out.println("inside mail exception");
					e.printStackTrace();
				}
				return 1;
			}
		} catch (NoResultException e) 
		{
			map.addAttribute("msg", "Please enter valid email address");
			e.printStackTrace();
		}
		return 0;
	}
	
	@PostMapping("/otp")
	public Integer confirmOtp(@RequestParam("email") String email,@RequestBody Users u)
	{
		System.out.println("Email is"+email);
		Users user = iuserdao.findByEmail(email);
		System.out.println("1 user otp "+user.getOtp());
		
		System.out.println("=============");
		System.out.println(u.getOtp());
		System.out.println("=============");
		System.out.println("==================");
		System.out.println(user.getOtp());
		System.out.println("==================");
			
		
		if(user.getOtp().equals(u.getOtp()))
		{
			System.out.println("otp checked");
			return 1;
		}
		else
			return 0;
	}
	
	@PostMapping("/change")
	public Integer setPassword(@RequestParam("email") String email,@RequestBody Users u)
	{
		System.out.println("=============");
		System.out.println(u.getPassword());
		System.out.println("=================");
		System.out.println(email);
		String pass = u.getPassword();
		iuserdao.resetpassword(pass,email);
		return 1;
	
	}
	
	@GetMapping("/customer/{role}")
	public ResponseEntity<?> getAllCustomers(@PathVariable String role)
	
	{
		System.out.println(role);
		List<Users> user= iuserdao.getCustomers(role.toUpperCase());
		if(user.isEmpty())
			return new ResponseEntity<String> ("No customers Found",HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Users>>(user,HttpStatus.OK);
	}
	
	@GetMapping("/orders")
	public ResponseEntity<?> getAllOrders()
	{
		List<Orders> order=iuserdao.getOrders();
		if(order.isEmpty())
			return new ResponseEntity<String>("No Orders Found",HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Orders>>(order,HttpStatus.OK);
	}
	
	@GetMapping("/cart")
	public List<Orders> getCart(@RequestParam int id)
	{
		Users user = iuserdao.getUserById(id);
		System.out.println(user);
		List<Orders> order=iuserdao.getOrdersDtls(user);
		if(order!=null)
			return order;
		return null;
	}
	
	@GetMapping("/history")
	public List<Orders> getHistory(@RequestParam int id)
	{
		Users user = iuserdao.getUserById(id);
		System.out.println(user);
		List<Orders> order=iuserdao.getOrderHistory(user);
		if(order!=null)
			return order;
		return null;
	}
	
	@GetMapping("/updateStatus")
	public Integer updateStatus(@RequestParam int id)
	{
		System.out.println(id);
		Orders order = iuserdao.getOrderById(id);
		int i =iuserdao.updateDelStatus(order);
		if(i==1)
			return 1;
		return 0;
		
	}
	@GetMapping("/block")
public Integer blockUser(@RequestParam int id)
{
	System.out.println(id);
	return 0;
}
	@GetMapping("/remove")
	public Integer removeOrder(@RequestParam int id)
	{
		System.out.println(id);
		int i =iuserdao.rmvOrder(id);
		if(i==1)
			return 1;
		return 0;
	}
	
}
