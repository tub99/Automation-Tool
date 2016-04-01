package com.util;

import java.util.HashMap;

public class DataTypeManager {
	
	private HashMap<String,String> rdbmsToJava=new HashMap<String,String>();
	private HashMap<String,String> javaToRdbms=new HashMap<String,String>();
	
	public DataTypeManager()
	{
		populateJavaToRdbmsMap();
		populateRdbmsToJavaMap();
	}
	
	public String getJavaDataType(String rdbmsDataType)
	{
		return rdbmsToJava.get(rdbmsDataType);
	}
	
	public String getRDBMSDataType(String javaDataType)
	{		 
		return javaToRdbms.get(javaDataType);
	}
	
	private void populateJavaToRdbmsMap()
	{
		javaToRdbms.put("String", "varchar");
	}
	
	private void populateRdbmsToJavaMap()
	{
		rdbmsToJava.put("VARCHAR", "String");
		rdbmsToJava.put("varchar", "String");
		rdbmsToJava.put("INT", "int");
		rdbmsToJava.put("integer", "int");
		rdbmsToJava.put("decimal", "float");
		rdbmsToJava.put("char", "String");
		rdbmsToJava.put("date", "Date");
		rdbmsToJava.put("DOUBLE","double");
		rdbmsToJava.put("DATE","Date");
		rdbmsToJava.put("boolean", "boolean");
	}
	
}
