package com.test.jvm;

public class TestInit {
	 
	static{
		i = 0 ;
		System.out.println(TestInit.i);
		//error  System.out.println(i);
	}

	static int i= 1 ;
	
	public static void main(String[] args) {

	}

}
