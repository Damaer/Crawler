package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.model.Food;
import com.util.DBUtil;
public class Dao {
	public void addGoddess(Food food) throws SQLException{
		Connection connection=DBUtil.getConnection();
		String sql=""+
					"insert into fooddata "+
					" (id,class_name,class_id,name,anothor_name,pregnancy_can_eat,"+
					"pregnancy_instruction,postpartum_can_eat,postpartum_instruction,"
					+ "lactation_can_eat,lactation_instruction,baby_can_eat,baby_instruction,tips,pictrue)"+
					"values("+
					"?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setInt(1, food.getId());
		preparedStatement.setString(2, food.getClass_name());
		preparedStatement.setInt(3, food.getClass_id());
		preparedStatement.setString(4, food.getName());
		preparedStatement.setString(5,food.getAnother_name());
		preparedStatement.setString(6, food.getPregnancy_can_eat());
		preparedStatement.setString(7, food.getPregnancy_instruction());
		preparedStatement.setString(8, food.getPostpartum_can_eat());
		preparedStatement.setString(9, food.getPostpartum_instruction());
		preparedStatement.setString(10, food.getLactation_can_eat());
		preparedStatement.setString(11, food.getLactation_instruction());
		preparedStatement.setString(12, food.getBaby_can_eat());
		preparedStatement.setString(13, food.getBaby_instruction());
		preparedStatement.setString(14, food.getTips());
		preparedStatement.setString(15, food.getPictrueurl());
		preparedStatement.execute();
	}
}
