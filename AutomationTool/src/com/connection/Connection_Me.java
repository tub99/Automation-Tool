package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection_Me {
	Connection connection=null;
	
	
	public Connection getConnection() throws ClassNotFoundException,
	SQLException {
		// TODO Auto-generated method stub
		try
		{
			System.out.println("conneting to Database...");
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/library management system","root","");
			System.out.println("Connection Successful");

		}
		catch(ClassNotFoundException error)
		{
			System.out.println("Error:" + error.getMessage()); 
		}

		catch(SQLException error)
		{
			System.out.println("Error:" + error.getMessage());
		}
		return connection;

	}


}
