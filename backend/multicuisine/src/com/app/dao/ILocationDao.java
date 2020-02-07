package com.app.dao;

import java.util.ArrayList;

import com.app.pojos.Locations;

public interface ILocationDao {
	public ArrayList<Locations> getLocation(Locations loc);
}
