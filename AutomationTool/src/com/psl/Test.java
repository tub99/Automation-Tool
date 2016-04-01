package com.psl;

import java.sql.Connection;
import java.sql.SQLException;

import com.connection.Connection_Me;
import com.util.DAOGenerator;
import com.util.EntityGenerator;

// This class gives all the inputs
public class Test 
{
	public static void main(String[] args)
	{
		testEntityGeneration();
		//testDAOGeneration();
	}
	
	public static void testDAOGeneration()
	{
		Connection_Me cm=new Connection_Me();
		Connection c=null;
		DAOGenerator dao=new DAOGenerator();
		try{
			c = cm.getConnection();
			dao.generateDAO("transaction", c);
		}catch(Exception e){}
	}
	
	public static void testEntityGeneration()
	{
		
		Connection_Me cm=new Connection_Me();
		
		Connection c=null;
		try {
			c = cm.getConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		EntityGenerator eg=new EntityGenerator();
		DAOGenerator dao=new DAOGenerator();
		try {
			eg.generateEntity("teacher", c);
			//eg.generateEntity("transaction", c);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		TestInsert();
		
		
		//TestRetrieve();
		//TestUpdate();
		//TestDealete();
	}
	
	private static void TestInsert()
	{
		// Create an entity object
		// Fill in the object
		
		// Create a Dao object
		//Invoke insert method of DAo object to insert  the object into Rdbms		
	}

}


/* My queries:
 *1.Where should  the metadata be stored?
 * Through the Test Case we will be giving the value to the field/attributes?
 * More than 1 database are going to be created---> Should that be done Automatically? If Yes? How?
 */

