package com.util;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class EntityGenerator 
{
	
		
	
		//Generate Entity
		public void generateEntity(String Table_name,Connection con) throws Exception
		{	
				DataTypeManager dtg=new DataTypeManager();
				ArrayList<String> dataType=new ArrayList<String>();
				ArrayList<String> varName=new ArrayList<String>();
				
				// Find metadata thru conn
				DatabaseMetaData databaseMetaData=con.getMetaData();
			
				//Read MetaData from System table
				String   catalog           = null;
				String   schemaPattern     = null;
				String   tableNamePattern  = Table_name;
				String   columnNamePattern = null;
				
				

				ResultSet result = databaseMetaData.getColumns(catalog, schemaPattern,  tableNamePattern, columnNamePattern);
					
				//Open the entity source file in the write mode
				BufferedWriter bw=new BufferedWriter(new FileWriter("E:/Eclipse_for java_workspace/AutomationTool/"+Table_name+".java"));
				System.out.println("E:/Eclipse_for java_workspace/AutomationTool/"+Table_name+".java");
				
				//Write Class beginning:Create class in the file named as Table_name.java
				String fileLine="public class"+" "+Table_name+" {"+"\n";
				
				//StringBuffer sb = new StringBuffer(2000);
			
				//Iterate thru the metadata records with where clause=tab_name
				int i=0;
				while(result.next())
				{
					//Read field-name and data-type
					//String columnName = result.getString(4);
					//int    columnType = result.getInt(5);
					
					String columnName = result.getString("COLUMN_NAME");
				    String columnTypeName = result.getString("TYPE_NAME");	
				    
				    System.out.println(columnName +"  "+columnTypeName);
				    
				    dataType.add(dtg.getJavaDataType(columnTypeName));
					varName.add(columnName);

								
					//Convert RDBMS d-t to Java d-type
					//String columnTypeString=getJdbcTypeName(columnType);
					//dataType.add(dtg.getJavaDataType(columnTypeString));
					//varName.add(columnName);
					
					//Creating Java-Type Attribute Declaration
					fileLine=fileLine+"\t"+"private "+dataType.get(i)+" "+varName.get(i)+";"+"\n";
					i++;			
				}
			
				
				//Generating parametirized constructor
				fileLine+="\n public "+Table_name+"(";
				for(int k=0;k<varName.size();k++)
				{
					if(k!=varName.size()-1)
						fileLine+=dataType.get(k)+" "+varName.get(k)+",";
					else
						fileLine+=dataType.get(k)+" "+varName.get(k)+"){";
				}
				for(int k=0;k<varName.size();k++)
				{
					if(k!=varName.size()-1)
						fileLine+="\n"+"\t"+"this."+varName.get(k)+"="+varName.get(k)+";";
					else
						fileLine+="\n"+"\t"+"this."+varName.get(k)+"="+varName.get(k)+";"+"\n";
				}
				fileLine+="\t}";
				
				//Write Attribute Declaration
				
				fileLine=fileLine+"\n";
				
				for(int j=0;j<varName.size();j++)
				{
					// Generate JavaBean compatible  setter() method name  
					//Generate  Setters with proper Casing
					String x="("+dataType.get(j)+" "+varName.get(j)+")";
					fileLine=fileLine+"\t"+"public void "+" "+Util.generateJavaBeanSetter(varName.get(j))+x+"{"+"\n"+"\t"+"\t"+"this."+varName.get(j)+"="+varName.get(j)+";"+"\n"+"\t"+"}"+"\n\n";
					
					//Generate Getter with proper Casing
					if(j!=varName.size()-1)
						fileLine=fileLine+"\t"+"public "+dataType.get(j)+" "+Util.generateJavaBeanGetter(varName.get(j))+"{"+"\n"+"\t"+"\t"+"return "+varName.get(j)+";"+"\n"+"\t"+"}"+"\n\n";
					else
						fileLine=fileLine+"\t"+"public "+dataType.get(j)+" "+Util.generateJavaBeanGetter(varName.get(j))+"{"+"\n"+"\t"+"\t"+"return "+varName.get(j)+";"+"\n"+"\t"+"}"+"\n";
					
			
				}
				
				// Call endClass() method to provide:"}"
				fileLine=fileLine+"}";
				System.out.println(fileLine);
				
				// Writing to file
				try{
				bw.write(fileLine);
				bw.close();
				}catch(Exception e)
				{
					e.printStackTrace();
				}
	//static str
		}
		
		
		 public static String getJdbcTypeName(int jdbcType) {
			   
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
