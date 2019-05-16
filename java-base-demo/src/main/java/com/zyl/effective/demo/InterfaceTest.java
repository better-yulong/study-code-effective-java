package com.zyl.effective.demo;

public interface InterfaceTest {
	 //static String get(String a,String b);

}

/***
 * 如若在class中定义一个方法而不实现，如 static String get(String a,String b);会报错：This method requires a body instead of a semicolon，
 * 意为这个方法需要方法体,不能只有一个分号。
 * 
 * 如若在interface接口定义static方法，如static String get(String a,String b)
 * 则会报错：Illegal modifier for the interface method get; only public & abstract are permitted
 * 
 * */
