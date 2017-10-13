package com.action;

import java.sql.SQLException;

import com.dao.Dao;
import com.model.Food;

public class Action {
	public void add(Food food) throws SQLException{
		Dao dao=new Dao();
		dao.addGoddess(food);;
	}
}
