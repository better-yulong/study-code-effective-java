package com.test.jvm;

public class TestAnonymousClassLoader {

	public void sayHello(Anony anony){
		System.out.println("hello.anony");
	}
	
	
	public static void main(String[] args) {
		TestAnonymousClassLoader ts = new TestAnonymousClassLoader();
		ts.sayHello(new Anony());
		ts.sayHello(new Anony());
		for(long i=0;i<100000000000l;i++){
			
		}
	}

}
class Anony{
}

/**
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * */
