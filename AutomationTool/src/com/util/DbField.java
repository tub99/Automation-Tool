package com.util;

public class DbField {

	@Override
	public String toString() {
		return "DbField [name=" + name + ", dataType=" + dataType + ", isKey="
				+ isKey + "]";
	}
	String name;
	String dataType;
	boolean isKey;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public boolean isKey() {
		return isKey;
	}
	public void setKey(boolean isKey) {
		this.isKey = isKey;
	}
	
		
}
