package com.app.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.ICityDao;
import com.app.pojos.City;



@Repository
@Transactional
public class CityDao implements ICityDao {
	@Autowired
	SessionFactory sf;
	@Override
	public List<City> getAllCities() {
		{
			String jpql = "select c from City c left outer join fetch c.location";
			return sf.getCurrentSession().createQuery(jpql, City.class).getResultList();
		}
	}
}
