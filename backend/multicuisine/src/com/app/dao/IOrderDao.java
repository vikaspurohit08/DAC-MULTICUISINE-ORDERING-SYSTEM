package com.app.dao;

import com.app.pojos.FoodItems;

public interface IOrderDao {
	public Integer addToCart(FoodItems item, int rest_id,int user_id);
}
