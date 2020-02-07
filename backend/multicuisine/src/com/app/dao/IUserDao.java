package com.app.dao;
import java.util.List;

import com.app.pojos.*;
import com.google.protobuf.DescriptorProtos.SourceCodeInfo.Location;
public interface IUserDao {
	public Users registerUser(Users user);
	public Users getUserById(Integer u_id);
	
	public Integer addAddress(Locations loc,Users user);
	
	public Users login(Users user);
	public Users findByEmail(Users user);
	public Users findByEmail(String email);
	public int generateOtp();
	public int resetpassword(String email,String pass);
	public void updateOtp(Users user);
	
	public List<Users> getCustomers(String role);
	public List<Orders> getOrders();
	
	public List<Orders> getOrdersDtls(Users user);
	
	public List<Orders> getOrderHistory(Users user);
	
	public Integer updateDelStatus(Orders order);
	
	public Orders getOrderById(int id);
	
	public Integer removeFoodItems(int fid,int oid);
	
	public Integer rmvOrder(int id);
	
	
	
	
}
