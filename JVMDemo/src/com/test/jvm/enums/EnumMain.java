package com.test.jvm.enums;

public class EnumMain {

	public static void main(String[] args) {
		System.out.println(EnumTest.INSTANCE_A.getValue());
		System.out.println(EnumTest.INSTANCE_A.getType());

	}
	
	enum EnumTest {
		INSTANCE_A;
		
		static int value;
		public static int getValue(){
			return value;
		}
		
		static String type;
		public static String getType(){
			return type;
		}
	}

}


/*
 * 	0
	null
 * 1、编译器会自动生成一个继承java.lang.Enum的类EnumT，且生成默认的private构造方法，即不允许调用。
 * 2、INSTANCE 可看作EnumT类的变量，可等价理解为：public static final EnumT INSTANCE.
 * 
 * 
 * **/

