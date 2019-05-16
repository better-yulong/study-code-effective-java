package com.test.jvm;

public class TestExtendClass {

	public static void main(String[] args) {
		ParentClass pc =new ChildClass();
		pc.say();
		System.out.println(pc.a);
	}

}

class ParentClass{
	public static int a = 1;
	public static void say(){
		System.out.println("parent");
	}
}

class ChildClass extends ParentClass{
	public static int a = 2;
	public static void say(){
		System.out.println("child");
	}
}

/**
 * 解析调用：
 * 执行结果：parent
 * 
 * */
