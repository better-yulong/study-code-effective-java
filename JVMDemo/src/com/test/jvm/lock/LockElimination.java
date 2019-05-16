package com.test.jvm.lock;

public class LockElimination {

	public static void main(String[] args) {
		contactString("a","b","c");

	}
	
	public static String contactString(String s1, String s2 ,String s3){
		return s1 + s2 + s3 ;
	}

}
