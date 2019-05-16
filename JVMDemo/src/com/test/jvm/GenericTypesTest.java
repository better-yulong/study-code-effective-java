package com.test.jvm;

import java.util.ArrayList;

public class GenericTypesTest {
	
	public void testGenericTypeClear(){
			ArrayList<Integer> list = new ArrayList<Integer>();
			list.add(Integer.valueOf(1));
			Integer temp = list.get(0);  //编译后反编译Class文件实际就是对get返回的Object对象做的强制转换成Integer对象。
	}
	
	GenericTypesTest(){
		
	}
	
	
	/*public static String method1_1(ArrayList<String> list){
		System.out.println("invoke method method1_1(ArrayList<String> list)");
		return "";
	}
	public static Integer method1_1(ArrayList<Integer> list){
		System.out.println("invoke method method1_1(ArrayList<Integer> list)");
		return 1;
	}
	
	public static void main(String[] args){
		method1_1(new ArrayList<String>());
		method1_1(new ArrayList<Integer>());
		//该部分代码在JDK1.5及JDK1.6可编译运行通过，但JDK1.7则无法编译。

	}*/
	

}
