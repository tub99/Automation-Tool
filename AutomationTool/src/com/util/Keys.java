package com.util;

public class Keys {

	static int keyCount;
	private String name;
	private String datatype;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDatatype() {
		return datatype;
	}

	@Override
	public String toString() {
		return "Keys [name=" + name + ", datatype=" + datatype + "]";
	}

	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}

}
