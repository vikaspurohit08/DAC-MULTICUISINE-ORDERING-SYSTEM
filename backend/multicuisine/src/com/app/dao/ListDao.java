package com.app.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.RestType;



@Repository
@Transactional
public class ListDao implements IListDao {
	
	@Autowired
	SessionFactory sf;
	
	public RestType[] getRestauranTypes()
	{
		return RestType.values();
	}
	
	
	
}
