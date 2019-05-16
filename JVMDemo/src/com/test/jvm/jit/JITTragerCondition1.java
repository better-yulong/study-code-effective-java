package com.test.jvm.jit;

public class JITTragerCondition1 {

	public static final int NUM = 1500 ;
	
	public static int doubleValue(int i){
		//
		for(int j=0;j<100000;j++){
		 //int a = NUM ;
		}
		return i*2;
	}
	
	public static long calcSum(){
		long sum = 0 ;
		for(int i=0 ; i<=100; i++){
			sum += doubleValue(i);
		}
		return sum ;
	}
		
	public static void main(String[] args) {
		for(int i=0 ; i<NUM ; i++){
			calcSum();
		}

	}
	
	public static void foo(Object obj){
		if(obj != null){
			System.out.println("do something");
		}
	}
	
	public static void testInline(){
		Object obj = null ;
		foo(obj);
	}

}
