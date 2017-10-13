package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	private static String URL="jdbc:mysql://127.0.0.1:3306/fooddata";
	private static String USER="*****";
	private static String PASSWROD ="*******";
	private static Connection connection=null;
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection=DriverManager.getConnection(URL,USER,PASSWROD);
		} catch (ClassNotFoundException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConnection(){
		return connection;
	}
}
