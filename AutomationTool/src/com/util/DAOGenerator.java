package com.util;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// This is the class that generates the sql queries performs Data Control Purpose like IDU 
public class DAOGenerator 
{
	
	DataTypeManager dtg=new DataTypeManager();
	//EntityGenerator etg=new EntityGenerator ();
	ArrayList<Keys> alkey=new ArrayList<Keys>();
	ArrayList<DbField>  fields=new ArrayList<DbField>();
	ArrayList<String> keyField=new ArrayList<String>();
	
	public void generateDAO(String tableName,Connection con) throws Exception
	{
							
		//Open the entity source file in the write mode
		BufferedWriter bw=new BufferedWriter(new FileWriter("E:/Eclipse_for java_workspace/AutomationTool/"+tableName+"DAO"+".java"));
		//System.out.println("E:/Eclipse_for java_workspace/AutomationTool/"+tableName+"DAO"+".java");
		
	
		//Read MetaData from System table
		String   catalog           = null;
		String   schemaPattern     = null;
		String   tableNamePattern  = tableName;
		String   columnNamePattern = null;
		String   schema    = null;
		//String tn=tableName;

		DatabaseMetaData databaseMetaData=con.getMetaData();
		ResultSet resultSet = databaseMetaData.getColumns(catalog, schemaPattern,  tableNamePattern, columnNamePattern);
		//Getting primary keys
		ResultSet result = databaseMetaData.getPrimaryKeys(
			    catalog, schema, tableNamePattern);

		
		//Iterate thru the metadata records with where clause=tab_name
		while(resultSet.next())
		{
			DbField df=new DbField();
			String columnName = resultSet.getString("COLUMN_NAME");
		    String columnTypeName = resultSet.getString("TYPE_NAME");
		    
		    
		    df.setName(columnName);
		    df.setDataType(columnTypeName);
		    fields.add(df);
		   	
		    	
		}
		while(result.next())
		{
			String keycolumnName = result.getString("COLUMN_NAME");
			System.out.println(keycolumnName);
			keyField.add(keycolumnName);
		}
		setKeys(keyField,fields);
		


	     			
		// Write Class beginning:Create class in the file named as Table_name.java
		String fileLine="public class"+" "+tableName+"DAO {"+"\n";
		
		// Generate insert method
		fileLine+=this.generateInsertMethod(tableName,con);
		
			
		// Generate update method
		fileLine+=this.generateUpdateMethod(tableName,con);
		
		
		
		// Generate delete method
	
		fileLine+=this.generateDeleteMethod(tableName,con);
		
		fileLine+=this.generateRetrieveMethod(tableName,con);
		System.out.println(fileLine);
		
		// How do we know that id is going to be an int type?
		
		//fileLine+=this.generateDeleteMethod(id,con);
		// My problem is regarding update method and how to get the key field which will be updated
		// Generate retrieve method
		//public TableName retrieve(int id,Conn con){} select * from tab where....
		
		// Generate class end
		//fileLine=fileLine+"}";
		
		
		//Writing the string to the file
		try{
			bw.write(fileLine);
			//System.out.println("Write successful");
			bw.close();
		}catch(FileNotFoundException fe)
		{
			//System.out.println(fe);
		}
			
	}
	
	private String generateRetrieveMethod(String tableName, Connection con) {
		// TODO Auto-generated method stub
		int x=0;
		fillKeys();
		if(Keys.keyCount==1)
			x=0;
		else
			x=(int) (Math.random()*4);
		
		
	
		
		String retrieveStatement="\n\tpublic int searchDb("+ alkey.get(x).getDatatype().toLowerCase()+" "+alkey.get(x).getName()+", Connection con){\n\t\t";
		
		retrieveStatement+="String str = select * from "+tableName+" where this."+alkey.get(x).getName()+"="+alkey.get(x).getName()+";";
		
		retrieveStatement+="\n\t\tStatement st=con.createStatement();\n\t\t"+"int x=st.executeQuery(str);\n\t";
		retrieveStatement+="\treturn x;"+"\n\t}\n}";
		
		
		return retrieveStatement;
	}

	private void setKeys(ArrayList<String> keyField2, ArrayList<DbField> fields2) {
		
		for(int i=0;i<fields2.size();i++)
		{
			for(int j=0;j<keyField2.size();j++)
			{
				if(keyField2.get(j).equals(fields.get(i).getName()))
					fields.get(i).setKey(true);
			}
		}
		
	}
	/////////////////////////////////////////////////////////////////////////////////
	
	private void fillKeys()
	{
		for(int m=0;m<fields.size();m++)
		{
			if(fields.get(m).isKey==true)
			{
				Keys k=new Keys();
				k.setName(fields.get(m).name);
				k.setDatatype(fields.get(m).dataType);
				alkey.add(k);
				Keys.keyCount++;
				
			}
		}
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////
	private String generateDeleteMethod(String tableName, Connection con)
	{
		
		fillKeys(); 
		int x=0;
		if(Keys.keyCount==1)
			x=0;
		else
			x=(int) (Math.random()*4);
		
		String deleteStatement="\n\tpublic int deleteDb("+ alkey.get(x).getDatatype().toLowerCase()+" "+alkey.get(x).getName()+", Connection con){\n\t\t";
		
		
		deleteStatement+="String str = delete from "+tableName+" where this."+alkey.get(x).getName()+"="+alkey.get(x).getName()+";";
		
		
		deleteStatement+="\n\t\tStatement st=con.createStatement();\n\t\t"+"int x=st.executeUpdate(str);\n\t";
		deleteStatement+="\treturn x;"+"\n\t}";
		
		return deleteStatement;
		
	}

	private String generateUpdateMethod(String tableName, Connection con) {
		// TODO Auto-generated method stub
		//Generate Method begining -> insert(AccountMater obj, Coonection con){
			int keyCount=0,cnt=0;
			
			String updateStatement="\n\tpublic int update("+tableName+" obj, Connection con){\n";
			updateStatement+="\t\tString str = update "+tableName+" set";
			
			//Taking input as random
			int x=(int) (Math.random()*4);
			for(int i=0;i<fields.size();i++)
			{
				if(fields.get(i).isKey==false)
				{
				
					if(i!=fields.size()-1)
						updateStatement+=" obj."+fields.get(i).getName()+"=obj."+Util.generateJavaBeanGetter(fields.get(i).getName())+",";
					else
						updateStatement+=" obj."+fields.get(i).getName()+"=obj."+Util.generateJavaBeanGetter(fields.get(i).getName());

				}
			}
			//Counting number of keys-->>>. used for maintaining update syntax 
			for(int m=0;m<fields.size();m++)
			{
				if(fields.get(m).isKey==true)
					keyCount++;
			}
			
			//Checking keys and putting them in update statement
			for(int m=0;m<fields.size();m++)
			{
				if(fields.get(m).isKey==true)
				{
					++cnt;
					if(cnt==keyCount)
						updateStatement+=" where obj."+fields.get(m).getName()+"=obj."+Util.generateJavaBeanGetter(fields.get(m).getName())+";";
					else
						updateStatement+=" where obj."+fields.get(m).getName()+"=obj."+Util.generateJavaBeanGetter(fields.get(m).getName())+", ";
				}
			}
			
			updateStatement+="\n\t\tStatement st=con.createStatement();\n\t\t"+"int x=st.executeUpdate(str);\n\t";
			updateStatement+="\treturn x;"+"\n\t}";
			
			
		
		return updateStatement;
	}

	// Create  insert method
	private String generateInsertMethod(String tableName,Connection con) throws Exception
	{
		// Generate Method begining -> insert(AccountMater obj, Coonection con){
		String insertStatement="\n\tpublic int insert("+tableName+" obj, Connection con){\n"; 
		// Creating Insert Statement
		 insertStatement+="\tString str = insert into "+tableName+"(";
		for(int k=0;k<fields.size();k++)
		{
			if(k!=fields.size()-1)
			{
				insertStatement+="obj."+fields.get(k).name+",";
			
			}else
				insertStatement+="obj."+fields.get(k).name+") values(";
		}
		
		//Generating getters into values()
		for(int k=0;k<fields.size();k++)
		{
			if(k!=fields.size()-1)
			{
				insertStatement+="obj."+Util.generateJavaBeanGetter(fields.get(k).name)+",";
			
			}else
				insertStatement+="obj."+Util.generateJavaBeanGetter(fields.get(k).name)+");";
		}
		
		insertStatement+="\n\tStatement st=con.createStatement();\n\t"+"int x=st.executeUpdate(str);\n\t";
		//String condition="if(x>0)\n\t\t"+"System.out.println(x)\n\t"+"else\n\t\t"+"System.out.println(x)";
		insertStatement+="return x;"+"\n\t}";
		
		// Generate Method end -> }
		
		return insertStatement;
		/*Statement st=con.createStatement();
		int x=st.executeUpdate(str);
		if(x>0)
			System.out.println("Successfully inserted:"+x+" no of rows");
		else
			System.out.println("No rows added");
			*/
	}
	//Create Delete Method
	
	
	

	
	//Create update method
	/*private void update(Table_name obj,Connection con)
	{
		String updateStatement="String str=update into "+obj.getTable_name+" set "+ obj.setAge()=" " where id=""
		Statement st=con.createStatement();
		int x=st.executeUpdate(str);
		if(x>0)
			System.out.println("Successfully inserted:"+x+" no of rows");
		else
			System.out.println("No rows added");
	}
	// create Retrieve method
	
	//Create delete method
	*/
	
	
	 public static String getJdbcTypeName(int jdbcType) 
	 {
		   
		   Map map = new HashMap();

		    // Get all field in java.sql.Types
		    Field[] fields = java.sql.Types.class.getFields();
		    for (int i = 0; i < fields.length; i++) {
		      try {
		        String name = fields[i].getName();
		        Integer value = (Integer) fields[i].get(null);
		        map.put(value, name);
		      } catch (IllegalAccessException e) {
		      }
		    }
		   return ((String) map.get(jdbcType)).toLowerCase();
	 }
}
