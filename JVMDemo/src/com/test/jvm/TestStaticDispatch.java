package com.test.jvm;

import java.io.Serializable;

public class TestStaticDispatch {

	public static void sayHello(char c){
		System.out.println("hello.char");
	}
	
	public static void sayHello(Character c){
		System.out.println("hello.Chracter");
	}
	
	public static void sayHello(char... c){
		System.out.println("hello.char...");
	}
	
	public static void sayHello(Serializable c){
		System.out.println("hello.char");
	}
	
	public static void sayHello(int c){
		System.out.println("hello.int");
	}
	/*public static int sayHello(int c){
		System.out.println("hello.int");
		return 1;
	}*/
	
	
	public static void main(String[] args) {
		sayHello('a');
	}

}

/*
 * 静态分派：
 * 运行结果：
 * hello.char
 */