package com.util;

public class Util {
	public static String generateJavaBeanGetter(String attribute) {

		String getter = "get" + Character.toUpperCase(attribute.charAt(0))
				+ attribute.substring(1, attribute.length()) + "()";

		return getter;
	}

	public static String generateJavaBeanSetter(String attribute) {

		String setter = "set" + Character.toUpperCase(attribute.charAt(0))
				+ attribute.substring(1, attribute.length());

		return setter;
	}
}
