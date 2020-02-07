package com.app.dao;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.pojos.Locations;
import com.app.pojos.Users;

@Repository
@Transactional
public class LocationDao implements ILocationDao 
{
	@Autowired
	SessionFactory sf;

	@Override
	public ArrayList<Locations> getLocation(Locations loc) 
	{
		System.out.println("inside getLocation "+loc);
		String jpql = "select l from Locations l where l.area=:area and l.street=:street and l.l_id=:l_id";
		ArrayList<Locations> locations = new ArrayList<>();
		locations = (ArrayList<Locations>) sf.getCurrentSession().createQuery(jpql,Locations.class).setParameter("area",loc.getArea()).setParameter("street",loc.getStreet()).setParameter("l_id",loc.getL_id()).getResultList();
		if(locations!=null)
			return locations;
		return null;
	}

}
