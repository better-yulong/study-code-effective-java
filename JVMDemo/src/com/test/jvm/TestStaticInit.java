package com.test.jvm;

class Parent {
	
	static int a = 1;
	
	static {
		a = 2;
	}
	
}

public class TestStaticInit extends Parent {
	static int i = a;
	
	public static void main(String[] args) {
		
		System.out.println(i);  
		//result:2
		//执行顺序为：父类  a =1 ; a=2 ;   子类 i = a ,即 a =2
		
	}
}
